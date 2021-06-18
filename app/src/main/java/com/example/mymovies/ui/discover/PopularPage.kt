package com.example.mymovies.ui.discover

import androidx.lifecycle.MutableLiveData
import com.example.mymovies.ApiInterface
import com.example.mymovies.Movie
import com.example.mymovies.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PopularPage {

    var moviesData = MutableLiveData<ArrayList<Movie>>()
    private var BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"
    private val pages = arrayOf("1", "2", "3")

    // function to get default page to show on discover fragment
    fun getPopularPage(): MutableLiveData<ArrayList<Movie>>? {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)

        // get multiple pages at once
        for (i in 0 until pages.size) {
            val call = service.getMovies(api_key, language, pages[i])

            call.enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.code() == 200) {
                        val movies = response.body()!!

                        var listOfMovies = movies.results
                        var anotherListOfMovies = ArrayList<Movie>()

                        if (moviesData.value == null) {
                            anotherListOfMovies.addAll(listOfMovies)
                            moviesData.value = anotherListOfMovies
                        } else {
                            anotherListOfMovies.addAll(moviesData.value!!)
                            anotherListOfMovies.addAll(listOfMovies)
                            moviesData.value = anotherListOfMovies
                        }
                    }
                }
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    //TODO (1) consider error message here to log
                }
            })
        }

        return moviesData
    }




}