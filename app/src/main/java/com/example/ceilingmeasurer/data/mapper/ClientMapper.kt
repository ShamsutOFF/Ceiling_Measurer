package com.example.ceilingmeasurer.data.mapper

import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.domain.DomainToModelMapper
import com.example.ceilingmeasurer.domain.entities.Client

class ClientMapper : DomainToModelMapper<Client, ClientEntity> {
    override fun toModel(value: Client): ClientEntity {
        return ClientEntity(
            clientId = value.id,
            name = value.name,
            surname = value.surname,
            phone_number = value.phone_number,
            address = value.address,
            district = value.district,
            status = value.status
        )
    }

    override fun fromModel(value: ClientEntity): Client {
        return Client(
            id = value.clientId,
            name = value.name,
            surname = value.surname,
            phone_number = value.phone_number,
            address = value.address,
            district = value.district,
            status = value.status
        )
    }
}