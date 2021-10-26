package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import com.example.mymovies.Movie

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getMoviesNew(): LiveData<List<Movie>> = appDatabase
        .movieNewDao().getAllMovies()

    override suspend fun insertAllMoviesNew(movies: List<Movie>) = appDatabase
        .movieNewDao().insertAllMovies(movies)

}