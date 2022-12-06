package com.delaiglesia.moviesapp.data.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
)