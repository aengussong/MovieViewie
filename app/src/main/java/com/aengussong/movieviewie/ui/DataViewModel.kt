package com.aengussong.movieviewie.ui

import androidx.lifecycle.*
import com.aengussong.movieviewie.usecase.FetchAndSaveMoviesOnNeedUseCase
import com.aengussong.movieviewie.usecase.GetMoviesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DataViewModel(
    private val initDataOnNeedUseCase: FetchAndSaveMoviesOnNeedUseCase,
    private val getDataUseCase: GetMoviesUseCase
) : ViewModel() {
    init {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            _error.value = throwable
        }

        viewModelScope.launch(errorHandler) {
            initDataOnNeedUseCase.execute()
        }
    }

    private val _error = MutableLiveData<Throwable>()
    val errors: LiveData<Throwable>
        get() = _error


    val moviesData = liveData {
        val data = getDataUseCase.execute()
        emitSource(data)
    }


}