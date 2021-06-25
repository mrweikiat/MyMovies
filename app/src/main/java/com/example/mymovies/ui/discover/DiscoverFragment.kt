package com.example.mymovies.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDiscoverBinding
import com.example.mymovies.ui.MovieDetails.MovieDetailsFragment


class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private lateinit var gridView: GridView

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

        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->

                gridView = root.findViewById(R.id.gridView)
                val mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter

                gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->

                    val intent = Intent (activity, MovieDetailsFragment::class.java)
                    // TODO try to pass as a json

                    intent.putExtra("MOVIE_NAME", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.title
                        .toString())

                    intent.putExtra("MOVIE_BACKDROP", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.backdrop
                        .toString())

                    intent.putExtra("MOVIE_POSTER", discoverViewModel
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

                    intent.putExtra("MOVIE_RATING", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.rating
                        .toString())

                    intent.putExtra("MOVIE_LANGUAGE", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.language
                        .toString())

                    intent.putExtra("MOVIE_RELEASE_DATE", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.releaseDate
                        .toString())

                    intent.putExtra("MOVIE_ID", discoverViewModel
                        .moviesData
                        ?.value
                        ?.get(position)
                        ?.movie_id
                        .toString())

                    activity?.startActivity(intent)
                }
                // for options sorting
                setHasOptionsMenu(true)
            }
        )


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

        discoverViewModel.getTopRatedMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                val mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
            }
        )

    }

    // fun to re-populate gridview to popular movies
    private fun makePopularMoviesList() {
        discoverViewModel.clearList()

        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                val mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
            }
        )
    }

    // fun to re-populate gridview to popular movies
    private fun makeNowPlayingMoviesList() {

        discoverViewModel.clearList()

        discoverViewModel.getNowPlayingMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                val mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
            }
        )
    }
}
