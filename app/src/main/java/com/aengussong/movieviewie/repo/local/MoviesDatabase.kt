package com.aengussong.movieviewie.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aengussong.movieviewie.repo.local.dao.MoviesDao
import com.aengussong.movieviewie.repo.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}