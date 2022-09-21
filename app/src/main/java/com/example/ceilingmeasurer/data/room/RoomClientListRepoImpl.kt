package com.example.ceilingmeasurer.data.room

import com.example.ceilingmeasurer.data.room.dao.ClientDAO
import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.entities.Client

class RoomClientListRepoImpl(private val dao: ClientDAO) : ClientListRepo {
    override suspend fun getClientList(): List<Client> {
        val tempList = dao.getAllClients()
        val returnList = mutableListOf<Client>()
        for (clientEntity in tempList) {
            returnList.add(
                Client(
                    id = clientEntity.clientId,
                    name = clientEntity.name,
                    surname = clientEntity.surname,
                    phone_number = clientEntity.phone_number,
                    address = clientEntity.address,
                    district = clientEntity.district,
                    status = clientEntity.status
                )
            )
        }
        return returnList
    }

    override suspend fun saveClient(client: Client) {
        dao.insertNewClient(
            ClientEntity(
                name = client.name,
                surname = client.surname,
                phone_number = client.phone_number,
                address = client.address,
                district = client.district,
                status = client.status
            )
        )
    }
}