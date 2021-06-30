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
    @SerializedName("backdrop_path")
    var backdrop: String? = null
    @SerializedName("id")
    var movie_id : String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("poster_path")
    var poster: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("vote_average")
    var rating: String? = null
    @SerializedName("original_language")
    var language: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("vote_count")
    var vote_count: String? = null

}