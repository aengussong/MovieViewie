package com.aengussong.movieviewie.di

import com.aengussong.imageloadrr.ImageLoadrr
import com.aengussong.movieviewie.repo.global.MoviesService
import com.aengussong.movieviewie.util.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MoviesService::class.java) }

    single { ImageLoadrr() }
}