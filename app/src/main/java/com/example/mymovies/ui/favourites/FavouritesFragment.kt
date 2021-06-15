package com.example.mymovies.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.MyFavouriteAdapter
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentFavouritesBinding
import com.google.android.material.snackbar.Snackbar

class FavouritesFragment : Fragment() {

    private lateinit var favouritesViewModel: FavouritesViewModel
    private var _binding: FragmentFavouritesBinding? = null

    private lateinit var gridView: GridView

    // dummy data for movies
    private var movieNames = arrayOf(
        "movie 1", "movie 2", "movie 3",
        "movie 4", "movie 5", "movie 6",
        "movie 7","movie 8","movie 9",
        "movie 1","movie 1","movie 1",
        "movie 1","movie 1","movie 1",
        "movie 1","movie 1","movie 1",
        "movie 1","movie 1","movie 1",
        "movie 1","movie 1","movie 1"
    )

    // dummy images for movies
    private var movieImages = intArrayOf(
        R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp, R.drawable.ic_notifications_black_24dp,
    )

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




        //val textView: TextView = binding.textFavourites
        //favouritesViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        //})

        gridView = root.findViewById(R.id.gridViewFavourite)

        // adapter for movie list
        val mainAdapter = MyFavouriteAdapter(this@FavouritesFragment, movieNames, movieImages)

        gridView.adapter = mainAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view: View, position: Int, id: Long ->
            // Write code to perform action when item is clicked.
            Snackbar.make(requireView(), "Clicked", Snackbar.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}