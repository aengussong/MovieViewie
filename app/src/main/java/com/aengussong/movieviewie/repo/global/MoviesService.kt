package com.aengussong.movieviewie.repo.global

import retrofit2.http.GET

interface MoviesService{

    @GET("test.json")
    suspend fun getMovies()
}