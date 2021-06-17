package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiscoverViewModel : ViewModel() {

    private var _movies = MutableLiveData<ArrayList<String>>().apply {
        value = arrayListOf()
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Discover Fragment"
    }

   fun getMovies(arr: ArrayList<String>) : ArrayList<String> {

       return arr

   }


}