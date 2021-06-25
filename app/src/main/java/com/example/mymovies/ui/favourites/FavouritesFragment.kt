package com.example.mymovies.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.MyFavouriteAdapter
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentFavouritesBinding
import com.example.mymovies.ui.MovieDetails.MovieDetailsFragment

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: FavouritesViewModel
    private var _binding: FragmentFavouritesBinding? = null

    private lateinit var gridView: GridView
    private var image_URL = "https://image.tmdb.org/t/p/original"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var movieNames = ArrayList<String>()
        var movieImages = ArrayList<String>()


        /**
         * val movieList = favouritesViewModel.getFavouriteMovies()

        for (movie in movieList) {
        movieNames.add(movie.title.toString())
        val str = image_URL + movie.poster.toString()
        movieImages.add(str)
        }
         */


        gridView = root.findViewById(R.id.gridViewFavourite)

        // adapter for movie list
        val mainAdapter = MyFavouriteAdapter(this@FavouritesFragment, movieNames, movieImages)

        gridView.adapter = mainAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->
            // Write code to perform action when item is clicked.
            //view.findNavController().navigate(R.id.action_navigation_favourites_to_navigation_details)
            val intent = Intent (activity, MovieDetailsFragment::class.java)
            activity?.startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}