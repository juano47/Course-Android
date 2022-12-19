package com.delaiglesia.newsapp.domain.usecase

import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

        fun execute(): Flow<List<Article>> = newsRepository.getSavedNews()

}