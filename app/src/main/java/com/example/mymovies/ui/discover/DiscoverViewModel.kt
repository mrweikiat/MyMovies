package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie
import com.example.mymovies.ui.discover.NowPlayingPage.getNowPlayingPage
import com.example.mymovies.ui.discover.PopularPage.getPopularPage
import com.example.mymovies.ui.discover.TopRatedPage.getTopRatedPage

class DiscoverViewModel : ViewModel() {

    var moviesData : MutableLiveData<ArrayList<Movie>>? = null
    var favouriteMoviesData = MutableLiveData<ArrayList<Movie>>()
    var movie = MutableLiveData<Movie>()

   fun getMovies(): LiveData<ArrayList<Movie>>? {
       moviesData = getPopularPage()
       return moviesData
   }

    fun getTopRatedMovies(): LiveData<ArrayList<Movie>>? {
        moviesData = getTopRatedPage()
        return moviesData
    }

    fun getNowPlayingMovies(): LiveData<ArrayList<Movie>>? {
        moviesData = getNowPlayingPage()
        return moviesData
    }

    fun clearList(){
        moviesData = null
    }

    fun getCurrList(): LiveData<ArrayList<Movie>>? {
        return moviesData
    }

    fun getFavouriteList(): LiveData<ArrayList<Movie>> {
        return favouriteMoviesData
    }

    fun setSelectedMovie(index: Int, list: ArrayList<Movie>) {
        var _movie = list[index]
        movie.value = _movie
    }
}