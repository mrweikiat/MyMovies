package com.example.mymovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie

class DiscoverViewModel : ViewModel() {

    var moviesData : MutableLiveData<ArrayList<Movie>> = MutableLiveData(arrayListOf())
    var favouriteMoviesData : MutableLiveData<ArrayList<Movie>> = MutableLiveData(arrayListOf())
    var movie = MutableLiveData<Movie>()

    // method to update the VM using a movie from callback
    fun setSelectedMovieRV(m: Movie) {
        movie.value = m
    }

    fun addToFavourites(movie: Movie) {
        var movieList = favouriteMoviesData.value
        movieList!!.add(movie)
        favouriteMoviesData.value = movieList
    }

    fun checkDuplicate(movieID: String): Boolean {
        for (movie in favouriteMoviesData.value!!) {
            if (movie.movie_id == movieID) {
                return true
            }
        }
        return false
    }

    fun removeFromFavourites(_movie: Movie) {
        var movieList = favouriteMoviesData.value
        movieList!!.remove(_movie)
        favouriteMoviesData.value = movieList
    }

    // empty data list
    fun clearMoviesData() {
        moviesData.value = ArrayList()
    }

}