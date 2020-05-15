package com.aengussong.movieviewie.repo

import androidx.lifecycle.LiveData
import com.aengussong.movieviewie.repo.global.model.ServiceMovieModel
import com.aengussong.movieviewie.repo.local.model.MovieEntity

interface Repo {
    suspend fun fetchMovies(): List<ServiceMovieModel>

    suspend fun saveMovies(movies:List<MovieEntity>)

    suspend fun getMovies(): LiveData<List<MovieEntity>>
}