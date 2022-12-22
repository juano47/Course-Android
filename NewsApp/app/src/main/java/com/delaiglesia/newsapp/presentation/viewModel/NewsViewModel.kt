package com.delaiglesia.newsapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.domain.usecase.*
import com.delaiglesia.newsapp.presentation.utils.CheckNetwork
import com.delaiglesia.newsapp.presentation.utils.CheckNetwork.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val getSavedArticleUseCase: GetSavedArticleUseCase
) : AndroidViewModel(app) {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val newsSearchedHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val savedNews: MutableLiveData<List<Article>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetworkAvailable(app)) {
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

    fun getSearchedNews(country: String, page: Int, searchQuery: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (isNetworkAvailable(app)) {
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

    //local data
    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        saveNewsUseCase.execute(article)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun getArticle(url: String) = liveData {
        getSavedArticleUseCase.getArticle(url).collect {
            emit(it)
        }
    }
}