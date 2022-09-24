package com.example.ceilingmeasurer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceilingmeasurer.data.room.dao.CeilingDAO
import com.example.ceilingmeasurer.data.room.dao.ClientDAO
import com.example.ceilingmeasurer.data.room.dao.OrdersDAO
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.data.room.tables.OrderEntity
import com.example.ceilingmeasurer.data.room.tables.PriceWorksAndMaterialsEntity

@Database(
    entities = arrayOf(
        ClientEntity::class,
        CeilingEntity::class,
        OrderEntity::class,
        PriceWorksAndMaterialsEntity::class,
    ), version = 1, exportSchema = false
)

abstract class RoomDb : RoomDatabase() {

    abstract fun ClientDAO(): ClientDAO
    abstract fun CeilingDAO(): CeilingDAO
}