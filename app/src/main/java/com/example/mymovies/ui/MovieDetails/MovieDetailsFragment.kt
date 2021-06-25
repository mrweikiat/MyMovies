package com.example.mymovies.ui.MovieDetails

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentNotificationsBinding
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovies.ui.favourites.FavouritesViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar


class MovieDetailsFragment : AppCompatActivity() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    private lateinit var favouritesViewModel: FavouritesViewModel

    private var _binding: FragmentNotificationsBinding? = null

    private var image_URL = "https://image.tmdb.org/t/p/original"


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favouritesViewModel =
            ViewModelProvider(this).get(FavouritesViewModel::class.java)

        setContentView(R.layout.movies_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val movieID = intent.getStringExtra("MOVIE_ID").toString()
        val movieName = intent.getStringExtra("MOVIE_NAME").toString()
        val movieDescription: String = intent.getStringExtra("MOVIE_DESCRIPTION").toString()
        val movieBackDropPath = intent.getStringExtra("MOVIE_BACKDROP").toString()
        val moviePosterPath = intent.getStringExtra("MOVIE_POSTER").toString()
        val movieRating = intent.getStringExtra("MOVIE_RATING").toString()
        val movieLanguage = intent.getStringExtra("MOVIE_LANGUAGE").toString()
        val movieReleaseDate = intent.getStringExtra("MOVIE_RELEASE_DATE").toString()

        setMovieRating(movieRating)
        setBackDropImage(movieBackDropPath)
        setPosterImage(moviePosterPath)
        setMovieDescription(movieDescription)
        setMovieLanguage(movieLanguage)
        setMovieReleaseDate(movieReleaseDate)
        setMovieID(movieID)
        setToolBar(movieName)

        var _movieID = movieID.toInt()

        var button = findViewById<Button>(R.id.add_to_favourites)

        button.setOnClickListener {
            favouritesViewModel.getMovie(_movieID)
            Snackbar.make(it, "HELLO", Snackbar.LENGTH_LONG).show()
        }


    }

    private fun setMovieRating(movieRating: String) {
        val movie_rating = findViewById<TextView>(R.id.movie_details_rating)
        movie_rating.text = "Rating: $movieRating"
    }

    private fun setBackDropImage(movieBackDropPath: String) {
        var movieDetailsImage = findViewById<ImageView>(R.id.movies_details_image)

        // load img using glide
        Glide.with(this)
            .load(image_URL + movieBackDropPath)
            .into(movieDetailsImage)
    }

    private fun setPosterImage(moviePosterPath: String) {
        var movieDetailsPoster = findViewById<ImageView>(R.id.movies_details_poster)

        Glide.with(this)
            .load(image_URL + moviePosterPath)
            .into(movieDetailsPoster)
    }

    private fun setMovieDescription(movieDescription: String) {
        val movie_description = findViewById<TextView>(R.id.movie_details_description)
        movie_description.text = movieDescription
    }

    private fun setToolBar(movieName: String) {
        val toolbar_layout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbar_layout.title = movieName
    }

    private fun setMovieLanguage(movieLanguage: String) {
        val movie_language = findViewById<TextView>(R.id.movie_details_language)
        movie_language.text = "Language: $movieLanguage"
    }

    private fun setMovieReleaseDate(movieReleaseDate: String) {
        val movie_release_date = findViewById<TextView>(R.id.movie_details_release_date)
        movie_release_date.text = "Release date: $movieReleaseDate"
    }

    private fun setMovieID(movie_id: String?) {
        val movie_ID = findViewById<TextView>(R.id.movie_details_movie_id)
        movie_ID.text = "Movie ID: ${movie_id.toString()}"
    }
}