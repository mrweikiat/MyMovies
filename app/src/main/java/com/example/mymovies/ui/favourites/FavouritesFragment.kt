package com.example.mymovies.ui.favourites

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.example.mymovies.databinding.LayoutFragmentFavouritesBinding
import com.example.mymovies.ui.discover.DiscoverViewModel

class FavouritesFragment : Fragment() {

    private var _binding: LayoutFragmentFavouritesBinding? = null
    private lateinit var model: DiscoverViewModel

    // gridlayout adapter
    private lateinit var recyclerFavouriteAdapter: RecyclerFavouriteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity)
            .supportActionBar?.setBackgroundDrawable(ColorDrawable(Color
                .parseColor("#212121")))

        return inflater.inflate(R.layout.layout_fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_favourite) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(
            context, 2, RecyclerView.VERTICAL, false)

        model.favouriteMoviesData?.observe(
            viewLifecycleOwner, Observer { moviesData ->
                recyclerFavouriteAdapter = RecyclerFavouriteAdapter(moviesData, ::onItemClick)
                recyclerView.adapter = recyclerFavouriteAdapter

            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(movie: Movie) {
        val action = FavouritesFragmentDirections
            .actionNavigationFavouritesToMovieDetailsFragment()
        model.setSelectedMovieRV(movie)
        requireView().findNavController().navigate(action)
    }
}