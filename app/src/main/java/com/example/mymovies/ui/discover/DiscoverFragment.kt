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
        discoverViewModel = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root

        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->

                gridView = root.findViewById(R.id.gridView)
                val mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter

                gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->

                    // TODO try to pass as a json
                    val action = DiscoverFragmentDirections.actionNavigationDiscoverToMovieDetailsFragment()

                    discoverViewModel.setSelectedMovie(position, newMovieData)

                    view.findNavController().navigate(action)

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
