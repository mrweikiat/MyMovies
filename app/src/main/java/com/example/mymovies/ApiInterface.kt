package com.example.mymovies

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface ApiInterface {

    @GET("popular?")
    fun getMovies(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String) : Call<Movies>

    @GET("top_rated?")
    fun getTopRated(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Call<Movies>

    @GET("now_playing?")
    fun getNowPlaying(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Call<Movies>

    @GET("/movie/{movie_id}?")
    fun getMovieUsingId(@Path("movie_id")movie_id: Int, @Query("api_key")api_key: String, @Query("language")language: String): Call<Movie>

}