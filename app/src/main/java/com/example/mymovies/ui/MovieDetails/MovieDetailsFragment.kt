package com.example.mymovies.ui.MovieDetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.example.mymovies.ui.discover.DiscoverViewModel
import com.google.android.material.snackbar.Snackbar

class MovieDetailsFragment : Fragment() {

    private var imageURL = "https://image.tmdb.org/t/p/original"
    private lateinit var model: DiscoverViewModel
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.movie_details_menu, menu)
    }

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
        (activity as AppCompatActivity).supportActionBar
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.movies_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(DiscoverViewModel::class.java)

        model.movie.observe(
            viewLifecycleOwner, Observer {
                _movie ->
                // move the setters to another method
                movie = _movie
                setMovie()
            }
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
            R.id.favourite_icon -> {
                if (model.checkDuplicate(movie.movie_id!!)) {
                    Snackbar.make(requireView(),"Already In Favourite List!",
                        Snackbar.LENGTH_LONG).show()
                } else {
                    model.addToFavourites(movie)
                    Snackbar.make(requireView(),"Added to favourite list",
                        Snackbar.LENGTH_LONG).show()
                }

                return true
            }
            R.id.remove_icon -> {
                if (model.checkDuplicate(movie.movie_id!!)) {
                    Snackbar.make(requireView(),"Removed from favourite list",
                        Snackbar.LENGTH_LONG).show()
                    model.removeFromFavourites(movie)
                } else {
                    Snackbar.make(requireView(),"Not in favourite list",
                        Snackbar.LENGTH_LONG).show()
                }

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // method to set all the data up
    private fun setMovie() {
        setMovieRating(movie.rating!!)
        setMovieTags(movie.genreIds!!)

        if (movie.backdrop != null) {
            setBackDropImage(movie.backdrop!!)
        }
        setPosterImage(movie.poster!!)
        setMovieDescription(movie.overview!!)
        setMovieLanguage(movie.language!!)
        setMovieReleaseDate(movie.releaseDate!!)
        setMovieID(movie.movie_id!!)
        setVoteCount(movie.voteCount!!)
        setTitle(movie.title!!)
    }

    private fun setMovieRating(_movieRating: String) {
        val movieRating = requireView().findViewById<TextView>(R.id.movie_details_rating)

        if (_movieRating.toDouble() < 1.0) {
            movieRating.text = "TBC"
        } else {
            movieRating.text = "$_movieRating"
        }
    }

    private fun setBackDropImage(movieBackDropPath: String) {
        var movieDetailsImage = requireView().findViewById<ImageView>(R.id.movies_details_image)

        // load img using glide
        Glide.with(this)
            .load(imageURL + movieBackDropPath)
            .into(movieDetailsImage)
    }

    private fun setPosterImage(moviePosterPath: String) {
        var movieDetailsPoster = requireView().findViewById<ImageView>(R.id.movies_details_poster)

        Glide.with(this)
            .load(imageURL + moviePosterPath)
            .into(movieDetailsPoster)
    }

    private fun setMovieDescription(_movieDescription: String) {
        val movieDescription = requireView().findViewById<TextView>(R.id.movie_details_description)
        movieDescription.text = _movieDescription
    }

    private fun setMovieLanguage(_movieLanguage: String) {
        val movieLanguage = requireView().findViewById<TextView>(R.id.movie_details_language)
        movieLanguage.text = "$_movieLanguage"
    }

    private fun setMovieReleaseDate(_movieReleaseDate: String) {
        val movieReleaseDate = requireView().findViewById<TextView>(R.id.movie_details_release_date)
        movieReleaseDate.text = "$_movieReleaseDate"
    }

    private fun setMovieID(movie_id: String?) {
        val movieId = requireView().findViewById<TextView>(R.id.movie_details_movie_id)
        movieId.text = "Movie ID: ${movie_id.toString()}"
    }

    private fun setVoteCount(movie_count: String?) {
        val movieCount = requireView().findViewById<TextView>(R.id.movie_details_votes)
        movieCount.text = "$movie_count votes"
    }

    private fun setTitle(movie_title: String?) {
        val movieTitle = requireView().findViewById<TextView>(R.id.movies_details_original_title)
        movieTitle.text = movie_title
    }

    private fun setMovieTags(genres: ArrayList<String>) {

        val tagView = requireView().findViewById<TextView>(R.id.movies_details_tags)
        var strHolder = ""

        var isFirst = true
        for (genre in genres) {
            if (!isFirst) {
                strHolder += ", $genre"
            } else {
                strHolder += "Tags: $genre"
                isFirst = false
            }
        }

        tagView.text = strHolder

    }

}