package com.example.ceilingmeasurer.data.fake

import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client

class FakeClientListRepoImpl : ClientListRepo {

    val client = mutableListOf<Client>(
        Client(1, "Andrei", "Roman", "1234", "Стекляный 13, кв22", "9", "Замер"),
        Client(2, "Adel", "Shamsutoff", "1235", "Виноградный 28, кв1", "7", "На монтаже"),
        Client(3, "Vladimir", "", "1236", "Лебединый 33, кв150", "5", "Закрыт"),
    )

    override suspend fun getClientList(): List<Client> {
        return client
    }

    override suspend fun saveClient(client: Client) {
        this.client.add(client)
    }

    override suspend fun updateClient(client: Client) {
        //nothing
    }

    override suspend fun deleteClient(client: Client) {
        //nothing
    }
}