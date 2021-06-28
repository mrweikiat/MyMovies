package com.example.mymovies.ui.MovieDetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentNotificationsBinding
import com.example.mymovies.ui.discover.DiscoverViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private var image_URL = "https://image.tmdb.org/t/p/original"


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        return inflater.inflate(R.layout.movies_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        model.movie.observe(
            viewLifecycleOwner, Observer {
                movie ->

                setMovieRating(movie.rating!!)
                setBackDropImage(movie.backdrop!!)
                setPosterImage(movie.poster!!)
                setMovieDescription(movie.overview!!)
                setMovieLanguage(movie.language!!)
                setMovieReleaseDate(movie.releaseDate!!)
                setMovieID(movie.movie_id!!)
                setToolBar(movie.title!!)

                var button = requireView().findViewById<Button>(R.id.add_to_favourites)
                button.setOnClickListener {
                    Snackbar.make(it, "HELLO", Snackbar.LENGTH_LONG).show()
                }

            }
        )

    }

    private fun setMovieRating(movieRating: String) {
        val movie_rating = requireView().findViewById<TextView>(R.id.movie_details_rating)
        movie_rating.text = "Rating: $movieRating"
    }

    private fun setBackDropImage(movieBackDropPath: String) {
        var movieDetailsImage = requireView().findViewById<ImageView>(R.id.movies_details_image)

        // load img using glide
        Glide.with(this)
            .load(image_URL + movieBackDropPath)
            .into(movieDetailsImage)
    }

    private fun setPosterImage(moviePosterPath: String) {
        var movieDetailsPoster = requireView().findViewById<ImageView>(R.id.movies_details_poster)

        Glide.with(this)
            .load(image_URL + moviePosterPath)
            .into(movieDetailsPoster)
    }

    private fun setMovieDescription(movieDescription: String) {
        val movie_description = requireView().findViewById<TextView>(R.id.movie_details_description)
        movie_description.text = movieDescription
    }

    private fun setToolBar(movieName: String) {
        val toolbar_layout = requireView().findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbar_layout.title = movieName
    }

    private fun setMovieLanguage(movieLanguage: String) {
        val movie_language = requireView().findViewById<TextView>(R.id.movie_details_language)
        movie_language.text = "Language: $movieLanguage"
    }

    private fun setMovieReleaseDate(movieReleaseDate: String) {
        val movie_release_date = requireView().findViewById<TextView>(R.id.movie_details_release_date)
        movie_release_date.text = "Release date: $movieReleaseDate"
    }

    private fun setMovieID(movie_id: String?) {
        val movie_ID = requireView().findViewById<TextView>(R.id.movie_details_movie_id)
        movie_ID.text = "Movie ID: ${movie_id.toString()}"
    }
}