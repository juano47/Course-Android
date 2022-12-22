package com.delaiglesia.newsapp.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.domain.usecase.GetNewsHeadlineUseCase
import com.delaiglesia.newsapp.domain.usecase.GetSearchedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val newsSearchedHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try { if (isNetworkAvailable(app)) {
                newsHeadlines.postValue(Resource.Loading())
                val response = getNewsHeadlineUseCase.execute(country, page)
                newsHeadlines.postValue(response)
            } else {
                newsHeadlines.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getSearchedNews(country: String, page: Int, searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
        try { if (isNetworkAvailable(app)) {
                newsSearchedHeadlines.postValue(Resource.Loading())
                val response = getSearchedNewsUseCase.execute(country, page, searchQuery)
                newsSearchedHeadlines.postValue(response)
            } else {
                newsSearchedHeadlines.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            newsSearchedHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}