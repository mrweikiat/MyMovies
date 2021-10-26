package com.example.mymovies.ui.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.mymovies.Movie
import kotlinx.coroutines.launch

class DiscoverViewModel : ViewModel() {

    var moviesData : MutableLiveData<ArrayList<Movie>> = MutableLiveData(arrayListOf())
    var favouriteMoviesData : MutableLiveData<ArrayList<Movie>> = MutableLiveData(arrayListOf())
    var movie = MutableLiveData<Movie>()
    var moviesDb : MutableLiveData<List<Movie>> = MutableLiveData(arrayListOf())

    init {
        fetchUsers()
        insertUsers()
    }

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

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                //val moviesFromDb = dbHelper.getMoviesNew()
            } catch (e: Exception) {
                //TODO handle exception
            }
        }
    }

    private fun insertUsers() {
        viewModelScope.launch {
            try {
                val moviesFromApi = moviesData
                //moviesDb = dbHelper.insertAllMoviesNew(moviesFromApi)
            } catch (e: Exception) {
                //TODO handle exception
            }
        }
    }


}