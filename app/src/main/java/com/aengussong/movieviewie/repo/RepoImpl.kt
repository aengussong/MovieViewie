package com.aengussong.movieviewie.repo

import androidx.lifecycle.LiveData
import com.aengussong.movieviewie.repo.global.MoviesService
import com.aengussong.movieviewie.repo.global.model.ServiceMovieModel
import com.aengussong.movieviewie.repo.local.dao.MoviesDao
import com.aengussong.movieviewie.repo.local.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImpl(
    private val service: MoviesService,
    private val moviesDao: MoviesDao
) : Repo {

    override suspend fun fetchMovies(): List<ServiceMovieModel> = withContext(Dispatchers.IO) {
        service.getMovies()
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) = withContext(Dispatchers.IO) {
        moviesDao.saveMovies(movies)
    }

    override suspend fun getMovies(): LiveData<List<MovieEntity>> = withContext(Dispatchers.IO) {
        moviesDao.getMovies()
    }

    override suspend fun getMoviesCount(): Int = withContext(Dispatchers.IO){
        moviesDao.countMovies()
    }
}