package com.example.ceilingmeasurer.model

import com.example.ceilingmeasurer.model.Tables.OrderEntity

interface LocalRepository {

    fun getAllOrders(): List<OrderEntity>

    fun saveOrderEntity(orderEntity: OrderEntity)

}