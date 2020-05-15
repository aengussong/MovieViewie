package com.aengussong.movieviewie.usecase

import com.aengussong.movieviewie.repo.Repo

class FetchAndSaveMoviesUseCase(private val repo: Repo) : UseCase() {

    suspend fun execute() {
        val movies = repo.fetchMovies()
        val dbMapped = movies.map { it.mapToDb() }
        repo.saveMovies(dbMapped)
    }
}