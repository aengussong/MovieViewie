package com.aengussong.movieviewie.di

import com.aengussong.movieviewie.repo.Repo
import com.aengussong.movieviewie.repo.RepoImpl
import com.aengussong.movieviewie.usecase.FetchAndSaveMoviesOnNeedUseCase
import com.aengussong.movieviewie.usecase.GetMoviesUseCase
import org.koin.dsl.module

val dataModule = module {
    single<Repo> { RepoImpl(get(), get()) }

    factory { FetchAndSaveMoviesOnNeedUseCase(get()) }

    factory { GetMoviesUseCase(get()) }
}