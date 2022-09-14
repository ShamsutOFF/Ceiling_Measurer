package com.example.ceilingmeasurer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel<T> : ViewModel() {
    protected val _liveData: MutableLiveData<T> = MutableLiveData()

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(error: Throwable) {
        //nothing
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.coroutineContext.cancel()
    }
}