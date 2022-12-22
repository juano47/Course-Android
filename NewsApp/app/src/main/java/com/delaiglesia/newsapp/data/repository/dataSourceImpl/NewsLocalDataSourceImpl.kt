package com.delaiglesia.newsapp.data.repository.dataSourceImpl

import com.delaiglesia.newsapp.data.db.dao.ArticleDao
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao): NewsLocalDataSource {
    override fun getArticlesFromDB(): Flow<List<Article>> = articleDao.getAllArticles()

    override suspend fun saveArticleToDB(article: Article) = articleDao.insert(article)

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article.id)

    override fun getArticleFromDB(url: String?): Flow<List<Article>> = articleDao.getArticleByUrl(url)
}