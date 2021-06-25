package com.example.mymovies.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie
import com.example.mymovies.ui.MovieDetails.GetMovieFromId.getMovieFromId
import com.example.mymovies.ui.MovieDetails.GetMovieFromId.movie

class FavouritesViewModel : ViewModel() {

    var movies: ArrayList<Movie>? = null

    fun getMovie(movieID: String) {
        var temp = getMovieFromId(movieID)
        movies?.add(temp)
        println(temp.title.toString())

    }
}