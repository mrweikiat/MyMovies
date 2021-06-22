package com.example.mymovies.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDiscoverBinding
import com.example.mymovies.ui.MovieDetails.MovieDetailsFragment
import com.google.android.material.snackbar.Snackbar


class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private lateinit var gridView: GridView

    private var image_URL = "https://image.tmdb.org/t/p/original"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProvider(this).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var movieNames = ArrayList<String>()
        var movieImages = ArrayList<String>()

        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                for (movie in newMovieData) {
                    movieNames.add(movie.title!!)
                    val path = image_URL + movie.poster!!
                    movieImages.add(path)
                }
            }
        )

        // for options sorting
        setHasOptionsMenu(true)

        gridView = root.findViewById(R.id.gridView)
        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
        gridView.adapter = mainAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->

            //view.findNavController().navigate(R.id.action_navigation_discover_to_navigation_details)
            val intent = Intent (activity, MovieDetailsFragment::class.java)
            intent.putExtra("MOVIE_NAME", discoverViewModel
                .moviesData
                ?.value
                ?.get(position)
                ?.title
                .toString())

            intent.putExtra("MOVIE_IMAGE", discoverViewModel
                .moviesData
                ?.value
                ?.get(position)
                ?.poster
                .toString())

            intent.putExtra("MOVIE_DESCRIPTION", discoverViewModel
                .moviesData
                ?.value
                ?.get(position)
                ?.overview
                .toString())

            activity?.startActivity(intent)
        }


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

        discoverViewModel.clearList()

        var movieNames = ArrayList<String>()
        var movieImages = ArrayList<String>()

        discoverViewModel.getTopRatedMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                for (movie in newMovieData) {
                    movieNames.add(movie.title!!)
                    val path = image_URL + movie.poster!!
                    movieImages.add(path)
                }
            }
        )

        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
        gridView.adapter = mainAdapter



    }

    // fun to re-populate gridview to popular movies
    private fun makePopularMoviesList() {
        discoverViewModel.clearList()

        var movieNames = ArrayList<String>()
        var movieImages = ArrayList<String>()

        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                for (movie in newMovieData) {
                    movieNames.add(movie.title!!)
                    val path = image_URL + movie.poster!!
                    movieImages.add(path)
                }
            }
        )

        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
        gridView.adapter = mainAdapter

    }

    // fun to re-populate gridview to popular movies
    private fun makeNowPlayingMoviesList() {
        discoverViewModel.clearList()

        var movieNames = ArrayList<String>()
        var movieImages = ArrayList<String>()

        discoverViewModel.getNowPlayingMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                for (movie in newMovieData) {
                    movieNames.add(movie.title!!)
                    val path = image_URL + movie.poster!!
                    movieImages.add(path)
                }
            }
        )

        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
        gridView.adapter = mainAdapter
    }
}
