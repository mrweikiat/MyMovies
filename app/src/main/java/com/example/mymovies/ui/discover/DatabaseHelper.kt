package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import com.example.mymovies.Movie

interface DatabaseHelper {

    suspend fun getMoviesNew(): LiveData<List<Movie>>

    suspend fun insertAllMoviesNew(movies: List<Movie>)

}