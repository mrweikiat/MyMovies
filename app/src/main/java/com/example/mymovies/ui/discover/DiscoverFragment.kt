package com.example.mymovies.ui.discover

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
import com.google.android.material.snackbar.Snackbar


class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private lateinit var gridView: GridView

    private var image_URL = "https://image.tmdb.org/t/p/original"

    var movieNames = arrayListOf<String>()
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

        gridView = root.findViewById(R.id.gridView)
        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)
        gridView.adapter = mainAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->

            view.findNavController().navigate(R.id.action_navigation_discover_to_navigation_details)
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
                Snackbar.make(requireView(), "sorting by popular", Snackbar.LENGTH_SHORT).show()
                return true
            }
            R.id.sort_top_rated ->{
                Snackbar.make(requireView(), "sorting by top rated", Snackbar.LENGTH_SHORT).show()
                return true
            }
            R.id.sort_now_playing ->{
                Snackbar.make(requireView(), "sorting by now playing", Snackbar.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
