package com.example.mymovies.ui.discover

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.example.mymovies.databinding.LayoutFragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: LayoutFragmentDiscoverBinding? = null

    // gridlayout recycler view
    private lateinit var recyclerDiscoverAdapter: RecyclerDiscoverAdapter
    private lateinit var recyclerView: RecyclerView

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

        recyclerView = view.findViewById(R.id.recycler_view_discover) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(
            context, 3, RecyclerView.VERTICAL, false)

        if (discoverViewModel.moviesData.value!!.isEmpty()) {
            discoverViewModel.getMovies()
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
        discoverViewModel.getTopRatedMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                recyclerDiscoverAdapter = RecyclerDiscoverAdapter(newMovieData, ::onItemClick)
                recyclerView.adapter = recyclerDiscoverAdapter
            }
        )
    }

    // fun to re-populate gridview to popular movies
    private fun makePopularMoviesList() {
        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                recyclerDiscoverAdapter = RecyclerDiscoverAdapter(newMovieData, ::onItemClick)
                recyclerView.adapter = recyclerDiscoverAdapter
            }
        )
    }

    // fun to re-populate gridview to now playing movies
    private fun makeNowPlayingMoviesList() {
        discoverViewModel.getNowPlayingMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                recyclerDiscoverAdapter = RecyclerDiscoverAdapter(newMovieData, ::onItemClick)
                recyclerView.adapter = recyclerDiscoverAdapter
            }
        )
    }

    // fun to set curr movie as destination and navigate to fragment
    private fun onItemClick(movie: Movie) {
        val action = DiscoverFragmentDirections.actionNavigationDiscoverToMovieDetailsFragment()
        discoverViewModel.setSelectedMovieRV(movie)
        requireView().findNavController().navigate(action)
    }
}
