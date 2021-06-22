package com.example.mymovies.ui.MovieDetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.mymovies.R
import com.example.mymovies.databinding.FragmentNotificationsBinding
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import java.net.URI


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
        val movieImagePath = intent.getStringExtra("MOVIE_IMAGE").toString()

        val toolbar_layout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbar_layout.title = movieName

        var movieDetailsImage = findViewById<ImageView>(R.id.movie_details_image)

        // load img using glide
        Glide.with(this)
            .load(image_URL + movieImagePath)
            .into(movieDetailsImage)

        val movieDescription: String = intent.getStringExtra("MOVIE_DESCRIPTION").toString()
        val movie_description = findViewById<TextView>(R.id.movie_details_description)

        if (movie_description != null) {
            movie_description.text = movieDescription
        }



    }
}