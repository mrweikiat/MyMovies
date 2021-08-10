package com.example.mymovies.ui.discover

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.ApiInterface
import com.example.mymovies.Movie
import com.example.mymovies.Movies
import com.example.mymovies.R
import com.example.mymovies.databinding.LayoutFragmentDiscoverBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: LayoutFragmentDiscoverBinding? = null

    // gridlayout recycler view
    private lateinit var recyclerDiscoverAdapter: RecyclerDiscoverAdapter
    private lateinit var recyclerView: RecyclerView

    // for Now playing RxJava
    private var mCompositeDisposable: CompositeDisposable? = null
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"
    private val pages = arrayOf("1", "2", "3")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity)
            .supportActionBar?.setBackgroundDrawable(
                ColorDrawable(Color.parseColor("#212121")))

        return inflater.inflate(R.layout.layout_fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        discoverViewModel = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)

        // init mCompos
        mCompositeDisposable = CompositeDisposable()

        recyclerView = view.findViewById(R.id.recycler_view_discover) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(
            context, 3, RecyclerView.VERTICAL, false)

        if (discoverViewModel.moviesData.value!!.isEmpty()) {
            // RxJava calls to populate an empty data list
            initLoad()
        }

        discoverViewModel.moviesData.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                recyclerDiscoverAdapter = RecyclerDiscoverAdapter(newMovieData, ::onItemClick)
                recyclerView.adapter = recyclerDiscoverAdapter
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        // clear to prevent mem leaks
        mCompositeDisposable?.clear()
    }

    //override to inflate menu res
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.popular_options, menu)
    }

    // override to take action when button is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.sort_popular -> {
                makePopularMoviesList()
                return true
            }
            R.id.sort_top_rated ->{
                makeTopRatedMoviesList()
                return true
            }
            R.id.sort_now_playing ->{
                makeNowPlayingMoviesList()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // fun to re-populate gridview to top rated movies
    private fun makeTopRatedMoviesList() {
        loadTopRated()
    }

    // fun to re-populate gridview to popular movies
    private fun makePopularMoviesList() {
        load()
    }

    // fun to re-populate gridview to now playing movies
    private fun makeNowPlayingMoviesList() {
        loadNowPlaying()
    }

    // fun to set curr movie as destination and navigate to fragment
    private fun onItemClick(movie: Movie) {
        val action = DiscoverFragmentDirections.actionNavigationDiscoverToMovieDetailsFragment()
        discoverViewModel.setSelectedMovieRV(movie)
        requireView().findNavController().navigate(action)
    }

    private fun initLoad() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(PopularPage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        for (i in pages) {
            mCompositeDisposable?.add(requestInterface.getMoviesData(api_key, language, i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleMultipleResponse))
        }
    }

    private fun load() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(PopularPage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        // clear data types
        discoverViewModel.clearMoviesData()

        for (i in pages) {
            mCompositeDisposable?.add(requestInterface.getMoviesData(api_key, language, i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleMultipleResponse))
        }

        recyclerDiscoverAdapter.refreshList(discoverViewModel.moviesData.value)
    }

    private fun loadNowPlaying() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(NowPlayingPage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        // clear data types
        discoverViewModel.clearMoviesData()

        for (i in pages) {
            mCompositeDisposable?.add(requestInterface.getNowPlayingData(api_key, language, i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleMultipleResponse))
        }

        recyclerDiscoverAdapter.refreshList(discoverViewModel.moviesData.value)

    }

    private fun loadTopRated() {

        val requestInterface = Retrofit.Builder()
            .baseUrl(TopRatedPage.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        // clear data types
        discoverViewModel.clearMoviesData()

        for (i in pages) {
            mCompositeDisposable?.add(requestInterface.getTopRatedData(api_key, language, i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleMultipleResponse))
        }

        recyclerDiscoverAdapter.refreshList(discoverViewModel.moviesData.value)

    }

    private fun handleMultipleResponse(list: Movies) {
        var holder = discoverViewModel.moviesData.value

        for (movie in list.results) {
            holder?.add(movie)
        }
        discoverViewModel.moviesData.value = holder

    }
}
