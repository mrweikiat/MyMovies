package com.example.mymovies.ui.discover

import androidx.lifecycle.MutableLiveData
import com.example.mymovies.ApiInterface
import com.example.mymovies.Movie
import com.example.mymovies.Movies
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object NowPlayingPage {

    var BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"

    fun getNowPlayingRequest(): ApiInterface {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

    }

    fun handleNowPlayingRequest(request: ApiInterface, page: String): Observable<Movies> {
        return request.getNowPlayingData(api_key, language, page)
    }


}