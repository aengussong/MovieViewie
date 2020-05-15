package com.aengussong.movieviewie.di

import com.aengussong.movieviewie.repo.Repo
import com.aengussong.movieviewie.repo.RepoImpl
import org.koin.dsl.module

val dataModule = module {
    single<Repo> { RepoImpl(get()) }
}