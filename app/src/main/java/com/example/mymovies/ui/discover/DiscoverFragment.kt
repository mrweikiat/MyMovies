package com.example.mymovies.ui.discover

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.ApiInterface
import com.example.mymovies.Movies
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDiscoverBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private lateinit var gridView: GridView

    private var BASE_URL = "https://api.themoviedb.org/3/movie/"
    private var image_URL = "https://image.tmdb.org/t/p/original"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"
    private val page = "1"

    var movieNames = arrayListOf<String>()

    // dummy images for movies
    private var movieImages = arrayListOf<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProvider(this).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // for options sorting
        setHasOptionsMenu(true)
        // default page
        getPopularPage(root)


        return root
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
                Snackbar.make(requireView(), "sorting by popular", Snackbar.LENGTH_SHORT).show()
                return true
            }
            R.id.sort_top_rated ->{
                Snackbar.make(requireView(), "sorting by top rated", Snackbar.LENGTH_LONG).show()
                return true
            }
            R.id.sort_now_playing ->{
                Snackbar.make(requireView(), "sorting by now playing", Snackbar.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // function to get default page to show on discover fragment
    private fun getPopularPage(root: View) {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)
        val call = service.getMovies(api_key, language, page)

        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == 200) {
                    val movies = response.body()!!

                    var listOfMovies = movies.results

                    for (movie in listOfMovies) {
                        movieNames.add(movie.originalTitle!!)
                        val path = image_URL + movie.poster!!
                        movieImages.add(path)
                    }

                    gridView = root.findViewById(R.id.gridView)
                    val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
                    gridView.adapter = mainAdapter
                    gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->
                        // Write code to perform action when item is clicked.
                        view.findNavController().navigate(R.id.action_navigation_discover_to_navigation_details)
                    }

                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Snackbar.make(requireView(), "error loading movies", Snackbar.LENGTH_SHORT).show()
            }
        })

    }

}
