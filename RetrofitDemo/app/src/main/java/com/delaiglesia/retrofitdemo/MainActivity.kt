package com.delaiglesia.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.delaiglesia.retrofitdemo.databinding.ActivityMainBinding
import com.delaiglesia.retrofitdemo.model.Albums
import retrofit2.Response
import androidx.databinding.DataBindingUtil
import com.delaiglesia.retrofitdemo.model.Album

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var restAlbumService:AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        restAlbumService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        getRequestWithPathParameter()
        getRequestWithQueryParameter()
    }

    private fun getRequestWithQueryParameter(){
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = restAlbumService.getAlbumsByUserId(3)
            emit(response)
        }

        responseLiveData.observe(this, {
            val albums = it.body()?.listIterator()
            if (albums!=null){
                while (albums.hasNext()){
                    val album = albums.next()
                    val result = " Album title :  ${album.title} \n" +
                            " Album id :  ${album.albumId} \n" +
                            " user id :  ${album.userId} \n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameter(){
        val pathResponse:LiveData<Response<Album>> = liveData {
            val response = restAlbumService.getAlbumById(3)
            emit(response)
        }

        pathResponse.observe(this, {
            val album = it.body()
            if (album != null){
                Log.i("Album", "Album id: ${album.albumId} - title: ${album.title}")
            }
        })
    }
}