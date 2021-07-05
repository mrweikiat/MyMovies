package com.example.mymovies.ui.favourites

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mymovies.MyFavouriteAdapter
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentFavouritesBinding
import com.example.mymovies.ui.discover.DiscoverViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    private lateinit var gridView: GridView
    // create class level adapter
    private lateinit var myFavouriteAdapter: MyFavouriteAdapter
    // create class level shareVM
    private lateinit var model: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity)
            .supportActionBar?.setBackgroundDrawable(ColorDrawable(Color
                .parseColor("#212121")))

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)
        // move gridView out of observe method
        gridView = view.findViewById(R.id.gridViewFavourite)

        model.favouriteMoviesData?.observe(
            viewLifecycleOwner, Observer {
                    MoviesData ->

                myFavouriteAdapter = MyFavouriteAdapter(this@FavouritesFragment, MoviesData)
                gridView.adapter = myFavouriteAdapter

            }
        )
        // move item click listener out of observe method
        gridView.onItemClickListener = AdapterView
            .OnItemClickListener { _, view: View, position: Int, _: Long ->

            val action = FavouritesFragmentDirections
                .actionNavigationFavouritesToMovieDetailsFragment()

            model.setSelectedMovie(position, model.getFavouriteMovieList())
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}