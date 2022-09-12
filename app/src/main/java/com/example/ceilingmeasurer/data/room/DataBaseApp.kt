package com.example.ceilingmeasurer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceilingmeasurer.data.room.dao.ClientsDAO
import com.example.ceilingmeasurer.data.room.dao.OrdersDAO
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.data.room.tables.OrderEntity
import com.example.ceilingmeasurer.data.room.tables.PriceWorksAndMaterialsEntity

@Database (entities = arrayOf(
    ClientEntity::class,
    CeilingEntity::class,
    OrderEntity::class,
    PriceWorksAndMaterialsEntity::class,
), version = 1, exportSchema = false)

abstract class DataBaseApp: RoomDatabase() {

    abstract fun ClientsDAO(): ClientsDAO

    abstract fun OrdersDAO(): OrdersDAO
}