package com.aengussong.movieviewie.repo.global

import com.aengussong.movieviewie.repo.global.model.ServiceMovieModel
import retrofit2.http.GET

interface MoviesService{

    @GET("test.json")
    suspend fun getMovies():List<ServiceMovieModel>
}