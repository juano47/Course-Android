package com.delaiglesia.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delaiglesia.newsapp.data.db.dao.ArticleDao
import com.delaiglesia.newsapp.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}