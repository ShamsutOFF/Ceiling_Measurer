package com.example.ceilingmeasurer.data.room

import com.example.ceilingmeasurer.data.mapper.ClientMapper
import com.example.ceilingmeasurer.data.room.dao.ClientDAO
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client

class RoomClientListRepoImpl(private val dao: ClientDAO) : ClientListRepo {
    override suspend fun getClientList(): List<Client> {
        val tempList = dao.getAllClients()
        val returnList = mutableListOf<Client>()
        for (clientEntity in tempList) {
            returnList.add(ClientMapper().fromModel(clientEntity))
        }
        return returnList
    }

    override suspend fun saveClient(client: Client) {
        dao.insertNewClient(ClientMapper().toModel(client))
    }

    override suspend fun updateClient(client: Client) {
        dao.updateClient(ClientMapper().toModel(client))
    }

    override suspend fun deleteClient(client: Client) {
        dao.deleteClient(ClientMapper().toModel(client))
    }
}