package com.delaiglesia.newsapp.domain.usecase

import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SaveNewsUseCase(private val newsRepository: NewsRepository) {

        suspend fun execute(article: Article) = newsRepository.saveNews(article)
}