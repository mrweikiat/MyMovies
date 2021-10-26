package com.example.mymovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("page")
    var page: String? = null

    @SerializedName("results")
    var results = ArrayList<Movie>()
}
@Entity (tableName = "movie_details_old")
data class Movie(
    @PrimaryKey
    @field:SerializedName("id") val movie_id: String?,
    @field:SerializedName("adult") val adult: String?,
    @field:SerializedName("backdrop_path") val backdrop: String?,
    @field:SerializedName("original_title") val originalTitle: String?,
    @field:SerializedName("overview") val overview: String?,
    @field:SerializedName("poster_path") val poster: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("vote_average") val rating: String?,
    @field:SerializedName("original_language") val language: String?,
    @field:SerializedName("release_date") val releaseDate: String?,
    @field:SerializedName("vote_count") val voteCount: String?,
    @field:SerializedName("genre_ids") val genreIds: ArrayList<String>?
)


