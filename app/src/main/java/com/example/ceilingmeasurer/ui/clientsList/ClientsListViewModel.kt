package com.example.ceilingmeasurer.ui.clientsList

import androidx.lifecycle.LiveData
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientsListViewModel(private val repo: ClientListRepo) : BaseViewModel<List<Client>>() {

    val clientList: LiveData<List<Client>> = _liveData

    fun getClientList() {
        cancelJob()
        viewModelCoroutineScope.launch { fetchClientList() }
    }

    private suspend fun fetchClientList() {
        withContext(Dispatchers.IO) {
            _liveData.postValue(repo.getClientList())
        }
    }
}