package com.aengussong.movieviewie.usecase

import com.aengussong.movieviewie.repo.Repo

class FetchAndSaveMoviesOnNeedUseCase(private val repo: Repo) : UseCase() {

    suspend fun execute() {
        val moviesCount = repo.getMoviesCount()
        if (moviesCount == 0) {
            val movies = repo.fetchMovies()
            val dbMapped = movies.map { it.mapToDb() }
            repo.saveMovies(dbMapped)
        }
    }
}