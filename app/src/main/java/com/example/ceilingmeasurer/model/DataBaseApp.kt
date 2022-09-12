package com.example.ceilingmeasurer.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceilingmeasurer.model.Tables.*

@Database (entities = arrayOf(
    ClientEntity::class,
    CeilingEntity::class,
    OrderEntity::class,
    PriceMaterialsEntity::class,
    PriceWorksEntity::class
), version = 1, exportSchema = false)

abstract class DataBaseApp: RoomDatabase() {

    abstract fun ClientsDAO(): ClientsDAO

    abstract fun OrdersDAO(): OrdersDAO
}