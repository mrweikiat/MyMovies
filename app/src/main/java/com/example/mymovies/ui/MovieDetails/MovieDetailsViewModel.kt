package com.example.mymovies.ui.MovieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie
import com.example.mymovies.ui.MovieDetails.GetMovieFromId.getMovieFromId

class MovieDetailsViewModel : ViewModel() {

    private var movieId : String? = null
    private var currMovie: MutableLiveData<Movie>? = null

    fun getSelectedMovieFromId(): LiveData<Movie> {
        currMovie = getMovieFromId(movieId.toString())
        return currMovie!!
    }

    fun setMovieID(movie_id: String) {
        movieId = movie_id
    }

    fun getMovieID(): String? {
        return movieId
    }

}