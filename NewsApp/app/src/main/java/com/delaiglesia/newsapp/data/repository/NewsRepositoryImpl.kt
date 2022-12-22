package com.delaiglesia.newsapp.data.repository

import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource): NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(country: String, page: Int, searchQuery: String): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedTopHeadlines(country, page, searchQuery))
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSavedNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("No articles found")
    }

}