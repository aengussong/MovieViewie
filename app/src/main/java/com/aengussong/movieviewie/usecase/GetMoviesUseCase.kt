package com.aengussong.movieviewie.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aengussong.movieviewie.model.Movie
import com.aengussong.movieviewie.repo.Repo

class GetMoviesUseCase(private val repo: Repo) : UseCase() {

    suspend fun execute(): LiveData<List<Movie>> {
        return Transformations.map(repo.getMovies()){list ->
            list.map { it.mapToUi() }
        }
    }
}