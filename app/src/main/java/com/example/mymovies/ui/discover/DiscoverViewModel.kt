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

    var sortByPopularity : Boolean = true // by default home page always shows popular movies
    var sortByTopRated : Boolean = false
    var sortByNowPlaying : Boolean = false


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

    fun setPopularityTrue() {
        sortByPopularity = true
    }

    fun setPopularityFalse() {
        sortByPopularity = false
    }

    fun setTopRatedTrue() {
        sortByTopRated = true
    }

    fun setTopRatedFalse() {
        sortByTopRated = false
    }

    fun setNowPlayingTrue() {
        sortByNowPlaying= true
    }

    fun setNowPlayingFalse() {
        sortByNowPlaying= false
    }

}