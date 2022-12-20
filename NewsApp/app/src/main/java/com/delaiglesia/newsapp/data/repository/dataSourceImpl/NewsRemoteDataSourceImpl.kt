package com.delaiglesia.newsapp.data.repository.dataSourceImpl

import com.delaiglesia.newsapp.data.api.NewsApiService
import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
    private val country: String,
    private val page: Int,
): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }
}