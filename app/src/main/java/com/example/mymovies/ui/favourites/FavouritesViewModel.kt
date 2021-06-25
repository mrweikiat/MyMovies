package com.example.mymovies.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie
import com.example.mymovies.ui.MovieDetails.GetMovieFromId.getMovieFromId
import com.example.mymovies.ui.MovieDetails.GetMovieFromId.movie

class FavouritesViewModel : ViewModel() {

    var movies: MutableLiveData<ArrayList<Movie>>? = null

    fun addMovie(movieID: Int) {
        val movie = getMovieFromId(movieID)
        movies?.value?.add(movie.value!!)
    }

    fun getMovies(): LiveData<ArrayList<Movie>>? {
        return movies
    }
}