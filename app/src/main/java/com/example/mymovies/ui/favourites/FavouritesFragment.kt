package com.example.mymovies.ui.favourites

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.SearchView
import android.widget.Toast
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
import com.google.android.material.snackbar.Snackbar

class FavouritesFragment : Fragment() {

    private var _binding: LayoutFragmentFavouritesBinding? = null
    private lateinit var model: DiscoverViewModel
    private lateinit var searchView : SearchView
    private lateinit var mHandler : Handler
    private lateinit var runnable: Runnable

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

        // search view
        searchView = view.findViewById(R.id.searchView)
        mHandler = Handler()

        model.favouriteMoviesData?.observe(
            viewLifecycleOwner, Observer { moviesData ->
                recyclerFavouriteAdapter = RecyclerFavouriteAdapter(moviesData, ::onItemClick)
                recyclerView.adapter = recyclerFavouriteAdapter

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query.toString() != null) {
                            val listToFilter = model.favouriteMoviesData.value
                            var filteredList = filterBaseOnText(listToFilter, query)
                            recyclerFavouriteAdapter = RecyclerFavouriteAdapter(filteredList, ::onItemClick)
                            recyclerView.adapter = recyclerFavouriteAdapter
                        }

                        return false
                    }
                    override fun onQueryTextChange(queryText: String?): Boolean {

                        runnable = Runnable {

                            mHandler.removeCallbacksAndMessages(null)

                            if (queryText.toString() != null) {
                                val listToFilter = model.favouriteMoviesData.value
                                var filteredList = filterBaseOnText(listToFilter, queryText)
                                recyclerFavouriteAdapter = RecyclerFavouriteAdapter(filteredList, ::onItemClick)
                                recyclerView.adapter = recyclerFavouriteAdapter
                            }

                            mHandler.postDelayed(runnable,500)
                        }
                        mHandler.postDelayed(runnable,500)



                        return false
                    }
                })
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

    // method to find movies that contains query text
    private fun filterBaseOnText(movies: ArrayList<Movie>?, queryText: String?) : ArrayList<Movie> {
        var filteredList = ArrayList<Movie>()
        if (movies != null) {
            for (movie in movies) {
                if (movie.title!!.contains(queryText!!, ignoreCase = true))
                    filteredList.add(movie)
            }
        }

        return filteredList
    }
}