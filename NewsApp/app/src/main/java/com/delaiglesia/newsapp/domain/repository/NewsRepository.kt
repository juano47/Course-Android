package com.delaiglesia.newsapp.domain.repository

import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(country: String, page: Int, searchQuery: String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteSavedNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}