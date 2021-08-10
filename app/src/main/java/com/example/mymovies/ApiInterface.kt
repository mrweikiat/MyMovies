package com.example.mymovies

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path
import io.reactivex.Observable

interface ApiInterface {

    @GET("popular")
    fun getMovies(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String) : Call<Movies>

    @GET("top_rated?")
    fun getTopRated(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Call<Movies>

    @GET("now_playing?")
    fun getNowPlaying(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Call<Movies>

    // RxJava

    @GET("now_playing?")
    fun getNowPlayingData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Observable<Movies>

    @GET("top_rated?")
    fun getTopRatedData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Observable<Movies>

    @GET("popular")
    fun getMoviesData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String) : Observable<Movies>

}