package com.delaiglesia.newsapp.domain.usecase

import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(): Resource<APIResponse> = newsRepository.getNewsHeadlines()
}