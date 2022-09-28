package com.example.ceilingmeasurer.ui.clientDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientDetailsViewModel(
    private val repoCeilings: ClientDetailsRepo,
    private val repoClients: ClientListRepo
) : BaseViewModel() {
    private val _liveCeilingList: MutableLiveData<List<Ceiling>> = MutableLiveData()
    val ceilingList: LiveData<List<Ceiling>> = _liveCeilingList

    fun getCeilings(client: Client) {
        cancelJob()
        viewModelCoroutineScope.launch { susGetCeilings(client) }
    }

    private suspend fun susGetCeilings(client: Client) {
        withContext(Dispatchers.IO) {
            _liveCeilingList.postValue(repoCeilings.getCeilings(client.id))
        }
    }

    fun insertNewCeiling(clientId: Int) {
        cancelJob()
        viewModelCoroutineScope.launch { susInsertNewCeiling(clientId) }
    }

    private suspend fun susInsertNewCeiling(clientId: Int) {
        withContext(Dispatchers.IO) { repoCeilings.saveCeiling(Ceiling(clientId = clientId)) }
    }

    fun updateClientCredentials(client: Client) {
        cancelJob()
        viewModelCoroutineScope.launch { updateClient(client) }
    }

    private suspend fun updateClient(client: Client) {
        withContext(Dispatchers.IO) {
            repoClients.updateClient(client)
        }
    }

    fun deleteCeiling(ceiling: Ceiling) {
//        cancelJob()
        viewModelCoroutineScope.launch { susDeleteCeiling(ceiling) }
    }

    private suspend fun susDeleteCeiling(ceiling: Ceiling) {
        withContext(Dispatchers.IO) {
            repoCeilings.deleteCeiling(ceiling)
        }
    }
}