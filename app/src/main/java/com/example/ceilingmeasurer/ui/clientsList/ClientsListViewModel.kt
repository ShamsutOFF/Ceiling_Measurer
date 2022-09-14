package com.example.ceilingmeasurer.ui.clientsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client
import kotlinx.coroutines.*

class ClientsListViewModel(private val repo: ClientListRepo) : ViewModel() {
    private val _liveData: MutableLiveData<List<Client>> = MutableLiveData()
    val liveData: LiveData<List<Client>> = _liveData

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    fun getClientList() {
        cancelJob()
        viewModelCoroutineScope.launch { fetchClientList() }
    }

    private suspend fun fetchClientList() {
        withContext(Dispatchers.IO) {
            _liveData.postValue(repo.getClientList())
        }
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handleError(error: Throwable) {
        //nothing
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.coroutineContext.cancel()
    }
}