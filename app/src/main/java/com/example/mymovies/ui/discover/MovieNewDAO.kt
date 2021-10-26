package com.example.mymovies.ui.discover

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovies.Movie

@Dao
interface MovieNewDAO {
    @Query("SELECT * FROM movie_details_old")
    suspend fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_details_old WHERE originalTitle LIKE :title")
    fun findByMovieTitle(title: String): Movie

    @Insert
    fun insertAll(vararg movie: Movie)

    @Insert
    suspend fun insertAllMovies(movies: List<Movie>)

    @Delete
    fun delete(movie: Movie)

}