package com.aengussong.movieviewie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aengussong.movieviewie.usecase.FetchAndSaveMoviesUseCase
import com.aengussong.movieviewie.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class DataViewModel(
    private val initDataUseCase: FetchAndSaveMoviesUseCase,
    private val getDataUseCase: GetMoviesUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            initDataUseCase.execute()
        }
    }

    val moviesData = liveData {
        val data = getDataUseCase.execute()
        emitSource(data)
    }
}