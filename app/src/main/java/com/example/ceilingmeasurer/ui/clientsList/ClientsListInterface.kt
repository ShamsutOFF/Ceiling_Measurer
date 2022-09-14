package com.example.ceilingmeasurer.ui.clientsList

import com.example.ceilingmeasurer.domain.entities.Client

interface ClientsListInterface {
    fun getClientList(): List<Client>
}