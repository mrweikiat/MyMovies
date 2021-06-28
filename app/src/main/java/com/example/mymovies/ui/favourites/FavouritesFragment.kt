package com.example.mymovies.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.MyFavouriteAdapter
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentFavouritesBinding
import com.example.mymovies.ui.MovieDetails.MovieDetailsFragment
import com.example.mymovies.ui.discover.DiscoverViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    private lateinit var gridView: GridView
    private var image_URL = "https://image.tmdb.org/t/p/original"

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)

        model.getFavouriteList().observe(
            viewLifecycleOwner, Observer {
                    thisMoviesData ->

                // adapter for movie list
                val mainAdapter = MyFavouriteAdapter(this@FavouritesFragment, thisMoviesData)
                gridView = view.findViewById(R.id.gridViewFavourite)
                gridView.adapter = mainAdapter

                gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->
                    val intent = Intent (activity, MovieDetailsFragment::class.java)
                    activity?.startActivity(intent)
                }

            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}