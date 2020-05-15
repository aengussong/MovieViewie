package com.aengussong.movieviewie.app

import android.app.Application
import com.aengussong.movieviewie.di.dataModule
import com.aengussong.movieviewie.di.dbModule
import com.aengussong.movieviewie.di.networkModule
import com.aengussong.movieviewie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                listOf(
                    networkModule,
                    dbModule,
                    dataModule,
                    viewModelModule
                )
            )
        }
    }
}