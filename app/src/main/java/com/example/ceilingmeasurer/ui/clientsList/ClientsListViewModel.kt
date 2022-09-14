package com.example.ceilingmeasurer.ui.clientsList

import androidx.lifecycle.ViewModel
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client

class ClientsListViewModel(private val repo: ClientListRepo) : ViewModel(), ClientsListInterface {
    override fun getClientList(): List<Client> {
        return repo.getClientList()
    }
}