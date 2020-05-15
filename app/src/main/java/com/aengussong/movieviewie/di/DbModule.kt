package com.aengussong.movieviewie.di

import androidx.room.Room
import com.aengussong.movieviewie.repo.local.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java,
            "moviesViewiesDb"
        ).build()
    }

    single {
        get<MoviesDatabase>().moviesDao()
    }
}