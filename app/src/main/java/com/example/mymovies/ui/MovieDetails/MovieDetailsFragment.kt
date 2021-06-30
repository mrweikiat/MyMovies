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
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.example.mymovies.ui.discover.DiscoverViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar


class MovieDetailsFragment : Fragment() {


    private var image_URL = "https://image.tmdb.org/t/p/original"


    // This property is only valid between onCreateView and
    // onDestroyView.
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
        var buttonAdd = requireView().findViewById<Button>(R.id.add_to_favourites)
        var buttonRemove = requireView().findViewById<Button>(R.id.remove_from_favourites)
        var _movie = Movie()

        model.movie.observe(
            viewLifecycleOwner, Observer {
                movie ->

                _movie = movie
                setMovieRating(movie.rating!!)
                setBackDropImage(movie.backdrop!!)
                setPosterImage(movie.poster!!)
                setMovieDescription(movie.overview!!)
                setMovieLanguage(movie.language!!)
                setMovieReleaseDate(movie.releaseDate!!)
                setMovieID(movie.movie_id!!)
                //setToolBar(movie.title!!)
                setVoteCount(movie.vote_count!!)
                setTitle(movie.title!!)

                buttonAdd.setOnClickListener {

                    var movieID = _movie.movie_id
                    if (model.checkDuplicate(movieID!!)) {
                        Snackbar.make(view,"Already In Favourite List!", Snackbar.LENGTH_LONG).show()
                    } else {
                        model.addToFavourites(_movie)
                        Snackbar.make(view,"Added to favourite list", Snackbar.LENGTH_LONG).show()
                    }
                }

                buttonRemove.setOnClickListener {
                    var movieID = _movie.movie_id
                    if (model.checkDuplicate(movieID!!)) {
                        Snackbar.make(view,"Removed from favourite list", Snackbar.LENGTH_LONG).show()
                        model.removeFromFavourites(_movie)
                    } else {
                        Snackbar.make(view,"Not in favourite list", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        )

    }

    private fun setMovieRating(movieRating: String) {
        val movie_rating = requireView().findViewById<TextView>(R.id.movie_details_rating)

        if (movieRating.toDouble() < 1.0) {
            movie_rating.text = "TBC"
        } else {
            movie_rating.text = "$movieRating"
        }
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
        movie_language.text = "$movieLanguage"
    }

    private fun setMovieReleaseDate(movieReleaseDate: String) {
        val movie_release_date = requireView().findViewById<TextView>(R.id.movie_details_release_date)
        movie_release_date.text = "$movieReleaseDate"
    }

    private fun setMovieID(movie_id: String?) {
        val movie_ID = requireView().findViewById<TextView>(R.id.movie_details_movie_id)
        movie_ID.text = "Movie ID: ${movie_id.toString()}"
    }

    private fun setVoteCount(movie_count: String?) {
        val movie_vote_count = requireView().findViewById<TextView>(R.id.movie_details_votes)
        movie_vote_count.text = "$movie_count votes"
    }

    private fun setTitle(movie_title: String?) {
        val movieTitle = requireView().findViewById<TextView>(R.id.movies_details_original_title)
        movieTitle.text = movie_title
    }
}