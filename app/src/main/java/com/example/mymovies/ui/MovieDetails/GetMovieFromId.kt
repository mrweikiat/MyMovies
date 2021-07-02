package com.example.mymovies.ui.MovieDetails

import androidx.lifecycle.MutableLiveData
import com.example.mymovies.ApiInterface
import com.example.mymovies.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetMovieFromId {

    var movie = MutableLiveData<Movie>()
    var _movie : Movie? = null
    val BASE_URL = "https://api.themoviedb.org/3/"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"

    fun getMovieFromId(movieId: Int): MutableLiveData<Movie> {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)

        val call = service.getMovieUsingId(movieId, api_key, language)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.code() == 200) {
                    val movies = response.body()!!
                    movie.value = movies

                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                //TODO (2) consider error message here to log
            }
        })

        return movie

    }




}