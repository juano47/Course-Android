package com.delaiglesia.retrofitdemo

import com.delaiglesia.retrofitdemo.model.Album
import com.delaiglesia.retrofitdemo.model.Albums
import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    // "/albums?userId=3"
    @GET("/albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId:Int) : Response<Albums>

    // "/albums/3"
    @GET("/albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId:Int) : Response<Album>

    @POST("/albums")
    suspend fun saveAlbum(@Body album:Album) : Response<Album>
}