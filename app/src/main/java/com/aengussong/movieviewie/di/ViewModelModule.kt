package com.aengussong.movieviewie.di

import com.aengussong.movieviewie.ui.DataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DataViewModel(get(), get()) }
}