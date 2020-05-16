package com.aengussong.movieviewie.repo.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aengussong.movieviewie.repo.local.model.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movieentity")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT COUNT(name) FROM movieentity")
    fun countMovies():Int
}