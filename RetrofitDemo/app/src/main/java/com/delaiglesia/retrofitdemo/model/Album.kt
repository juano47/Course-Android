package com.delaiglesia.retrofitdemo.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val albumId: Int,
    val title: String,
    val userId: Int
)