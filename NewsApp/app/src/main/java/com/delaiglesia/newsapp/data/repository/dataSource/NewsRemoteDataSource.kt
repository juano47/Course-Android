package com.delaiglesia.newsapp.data.repository.dataSource

import com.delaiglesia.newsapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
}