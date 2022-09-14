package com.example.ceilingmeasurer.data.fake

import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client

class FakeClientListRepoImpl : ClientListRepo {
    override suspend fun getClientList(): List<Client> {
        return listOf(
            Client(1, "Andrei", "Roman", "1234", "Стекляный 13, кв22", "9", "Замер"),
            Client(2, "Adel", "Shamsutoff", "1235", "Виноградный 28, кв1", "7", "На монтаже"),
            Client(3, "Vladimir", "", "1236", "Лебединый 33, кв150", "5", "Закрыт"),
        )
    }
}