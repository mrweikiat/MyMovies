package com.example.mymovies.ui.discover

import com.example.mymovies.ApiInterface
import com.example.mymovies.Movies
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object TopRatedPage {

    var BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"

    fun getTopRatedRequest(): ApiInterface {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

    }

    fun handleTopRatedRequest(request: ApiInterface, page: String): Observable<Movies> {
        return request.getTopRatedData(api_key, language, page)
    }
}