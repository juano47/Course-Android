package com.delaiglesia.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delaiglesia.moviesapp.data.db.dao.ArtistDao
import com.delaiglesia.moviesapp.data.db.dao.MovieDao
import com.delaiglesia.moviesapp.data.db.dao.TvShowDao
import com.delaiglesia.moviesapp.data.model.artist.Artist
import com.delaiglesia.moviesapp.data.model.movie.Movie
import com.delaiglesia.moviesapp.data.model.tvshow.TvShow

@Database(entities = [Movie::class, TvShow::class, Artist::class], version = 1, exportSchema = false)
abstract class TMDBDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun artistDao(): ArtistDao
}