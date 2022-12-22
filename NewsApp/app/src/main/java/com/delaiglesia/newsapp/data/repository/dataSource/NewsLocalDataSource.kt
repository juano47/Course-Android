package com.delaiglesia.newsapp.data.repository.dataSource

import com.delaiglesia.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    fun getArticlesFromDB(): Flow<List<Article>>
    suspend fun saveArticleToDB(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getArticleFromDB(url: String?): Flow<List<Article>>
}