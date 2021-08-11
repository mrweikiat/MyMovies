package com.example.mymovies

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path
import io.reactivex.Observable

interface ApiInterface {

    // RxJava
    @GET("now_playing?")
    fun getNowPlayingData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Observable<Movies>

    @GET("top_rated?")
    fun getTopRatedData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String): Observable<Movies>

    @GET("popular")
    fun getMoviesData(@Query("api_key")api_key: String, @Query("language")language: String, @Query("page")page: String) : Observable<Movies>

}