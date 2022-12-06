package com.delaiglesia.moviesapp.data


import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("results")
    val results: List<Movie>,

    )