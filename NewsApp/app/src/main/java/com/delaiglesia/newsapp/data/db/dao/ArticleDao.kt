package com.delaiglesia.newsapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delaiglesia.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM article WHERE url = :url")
    fun getArticleByUrl(url: String?): Flow<List<Article>>

    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM article WHERE id = :id")
    suspend fun deleteArticle(id: Int)

}