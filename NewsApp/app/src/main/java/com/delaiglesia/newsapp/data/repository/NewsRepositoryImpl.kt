package com.delaiglesia.newsapp.data.repository

import com.delaiglesia.newsapp.data.model.APIResponse
import com.delaiglesia.newsapp.data.model.Article
import com.delaiglesia.newsapp.data.repository.dataSource.NewsLocalDataSource
import com.delaiglesia.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.delaiglesia.newsapp.data.utils.Resource
import com.delaiglesia.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedTopHeadlines(
                country,
                page,
                searchQuery
            )
        )
    }

    override suspend fun saveArticle(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }


    override suspend fun deleteSavedArticle(article: Article) =
        newsLocalDataSource.deleteArticle(article)

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getArticlesFromDB()
    }

    override fun getArticle(url: String?): Flow<List<Article>> {
        return newsLocalDataSource.getArticleFromDB(url)
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