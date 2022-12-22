package com.delaiglesia.newsapp.domain.usecase

import com.delaiglesia.newsapp.domain.repository.NewsRepository

class GetSavedArticleUseCase(private val newsRepository: NewsRepository) {

    fun getArticle(url: String) = newsRepository.getArticle(url)
}