package com.example.mymovies.ui.MovieDetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentNotificationsBinding
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout


class MovieDetailsFragment : AppCompatActivity() {

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    private var image_URL = "https://image.tmdb.org/t/p/original"


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.movies_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val movieName = intent.getStringExtra("MOVIE_NAME").toString()
        val movieDescription: String = intent.getStringExtra("MOVIE_DESCRIPTION").toString()
        val movieBackDropPath = intent.getStringExtra("MOVIE_BACKDROP").toString()
        val movieRating = intent.getStringExtra("MOVIE_RATING").toString()
        val movieLanguage = intent.getStringExtra("MOVIE_LANGUAGE").toString()
        val movieReleaseDate = intent.getStringExtra("MOVIE_RELEASE_DATE").toString()

        setMovieRating(movieRating)
        setBackDropImage(movieBackDropPath)
        setMovieDescription(movieDescription)
        setMovieLanguage(movieLanguage)
        setMovieReleaseDate(movieReleaseDate)


        setToolBar(movieName)

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

}