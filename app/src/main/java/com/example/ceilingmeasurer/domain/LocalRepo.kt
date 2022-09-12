package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.data.room.tables.OrderEntity

interface LocalRepo {

    fun getAllOrders(): List<OrderEntity>

    fun saveOrderEntity(orderEntity: OrderEntity)

}