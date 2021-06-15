package com.example.mymovies.ui.discover

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mymovies.MainAdapter
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentDiscoverBinding
import com.google.android.material.snackbar.Snackbar

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
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
        R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
        R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,R.drawable.ic_notifications_black_24dp,
    )


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

        //val textView: TextView = binding.textDiscover

        //discoverViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        //})

        // for options sorting
        setHasOptionsMenu(true)


        gridView = root.findViewById(R.id.gridView)

        // adapter for movie list
        val mainAdapter = MainAdapter(this@DiscoverFragment, movieNames, movieImages)

        gridView.adapter = mainAdapter
        gridView.onItemClickListener =


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
                Snackbar.make(requireView(), "sorting by top rated", Snackbar.LENGTH_LONG).show()
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
