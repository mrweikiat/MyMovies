package com.example.mymovies.ui.discover

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_details")
data class MovieNew(
    @PrimaryKey
    @field:SerializedName("movie_id") val movieID: String?,
    @field:SerializedName("adult") val adult: String?,
    @field:SerializedName("backdrop") val backdrop: String?,
    @field:SerializedName("original_title") val originalTitle: String?,
    @field:SerializedName("overview") val overview: String?,
    @field:SerializedName("poster") val poster: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("rating") val rating: String?,
    @field:SerializedName("language") val language: String?,
    @field:SerializedName("releaseDate") val releaseDate: String?,
    @field:SerializedName("voteCount") val voteCount: String?,
    @field:SerializedName("genre_ids") val genreIds: ArrayList<String>?
)
