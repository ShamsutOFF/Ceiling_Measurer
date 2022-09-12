package com.example.ceilingmeasurer.model.Tables

import com.example.ceilingmeasurer.model.LocalRepository
import com.example.ceilingmeasurer.model.OrdersDAO
import java.util.*

class LocalRepositoryImpl(private val dao: OrdersDAO) : LocalRepository {

    override fun getAllOrders(): List<OrderEntity> {
        return dao.all()
            .map { entity ->
                OrderEntity(
                    id = (entity.id),
                    id_client = (entity.id_client),
                    status = (entity.status),
                    date_timestamp = (entity.date_timestamp),
                    date_work = (entity.date_work),
                    ceilings = (entity.ceilings),
                    description = (entity.description),
                    attachment = (entity.attachment)
                )
            }
    }

    //Нужно дописать поля ввода из EditText из xml
    override fun saveOrderEntity(orderEntity: OrderEntity) {
        return dao.insert(
            OrderEntity(
                id = 0,
                id_client = "0",
                status = "",
                date_timestamp = Date().time,
                date_work = "",
                ceilings = "",
                description = "",
                attachment = "0"
            )
        )
    }
}