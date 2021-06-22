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


object NowPlayingPage {

    var moviesData = MutableLiveData<ArrayList<Movie>>()
    private var BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val api_key = "a20f630ca428f9f3ad3d5f506f8e5101"
    private val language = "en-US"
    //private val pages = arrayOf("1", "2", "3")
    private val pages = arrayOf("1")

    // function to get default page to show on discover fragment
    fun getNowPlayingPage(): MutableLiveData<ArrayList<Movie>>? {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(ApiInterface::class.java)

        val call = service.getNowPlaying(api_key, language, pages[0])

        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.code() == 200) {
                    val movies = response.body()!!

                    moviesData.value = movies.results

                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                //TODO (3) consider error message here to log
            }
        })

        return moviesData
    }


}