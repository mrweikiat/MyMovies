package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovies.Movie
import com.example.mymovies.ui.discover.PopularPage.getPopularPage

class DiscoverViewModel : ViewModel() {

    var moviesData : MutableLiveData<ArrayList<Movie>>? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is Discover Fragment"
    }

   fun getMovies(): LiveData<ArrayList<Movie>>? {
       moviesData = getPopularPage()
       return moviesData
   }


}