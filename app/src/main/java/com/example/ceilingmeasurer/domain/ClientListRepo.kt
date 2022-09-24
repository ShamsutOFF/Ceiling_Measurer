package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Client

interface ClientListRepo {
    suspend fun getClientList(): List<Client>
    suspend fun saveClient(client: Client)
    suspend fun updateClient(client: Client)
    suspend fun deleteClient(client: Client)
}
