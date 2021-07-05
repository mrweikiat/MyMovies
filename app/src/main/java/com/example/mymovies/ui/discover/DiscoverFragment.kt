package com.example.mymovies.ui.discover

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private lateinit var gridView: GridView
    // Change mainAdapter to class level variable so
    // we dont need to create a new adapter everytime sort flags
    // are called
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#212121")))
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Moved this method out of observe
        setHasOptionsMenu(true)
        discoverViewModel = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)
        gridView = view.findViewById(R.id.gridView)

        if (discoverViewModel.moviesData.value!!.isEmpty()) {
            discoverViewModel.getMovies()
        }

        discoverViewModel.moviesData.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
            }
        )

        // move click listener out of observe
        // so we dont call everytime observe
        // method is called. Also removed
        // redundant variables in clickListener
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, view: View, position: Int, _: Long ->
            val action = DiscoverFragmentDirections.actionNavigationDiscoverToMovieDetailsFragment()
            discoverViewModel.setSelectedMovie(position, discoverViewModel.getMovieList())
            view.findNavController().navigate(action)
        }
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
                mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
                // removed redundant onClickListener in this method
            }
        )
    }

    // fun to re-populate gridview to popular movies
    private fun makePopularMoviesList() {
        discoverViewModel.getMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
                // removed redundant onClickListener in this method
            }
        )
    }

    // fun to re-populate gridview to now playing movies
    private fun makeNowPlayingMoviesList() {
        discoverViewModel.getNowPlayingMovies()!!.observe(
            viewLifecycleOwner,
            Observer { newMovieData ->
                mainAdapter = MainAdapter(this@DiscoverFragment, newMovieData)
                gridView.adapter = mainAdapter
                // removed redundant onClickListener in this method
            }
        )
    }
}
