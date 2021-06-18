package com.example.mymovies

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("page")
    var page: String? = null

    @SerializedName("results")
    var results = ArrayList<Movie>()
}

class Movie {
    @SerializedName("adult")
    var adult: String? = null
    @SerializedName("id")
    var movie_id : Int? = 0
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("poster_path")
    var poster: String? = null
    @SerializedName("title")
    var title: String? = null

}