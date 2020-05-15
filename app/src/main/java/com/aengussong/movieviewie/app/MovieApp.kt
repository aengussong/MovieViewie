package com.aengussong.movieviewie.app

import android.app.Application
import com.aengussong.movieviewie.di.viewModelModule
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule)
        }
    }
}