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
    var favouriteMoviesData : MutableLiveData<ArrayList<Movie>> = MutableLiveData(arrayListOf())
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

    fun setSelectedMovie(index: Int, list: ArrayList<Movie>) {
        var _movie = list[index]
        movie.value = _movie
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
}