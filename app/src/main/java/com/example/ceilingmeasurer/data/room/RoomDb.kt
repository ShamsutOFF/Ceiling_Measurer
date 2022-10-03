package com.example.ceilingmeasurer.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceilingmeasurer.data.room.dao.CeilingDAO
import com.example.ceilingmeasurer.data.room.dao.ClientDAO
import com.example.ceilingmeasurer.data.room.dao.MaterialsDAO
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.data.room.tables.MaterialEntity
import com.example.ceilingmeasurer.data.room.tables.OrderEntity

@Database(
    entities = arrayOf(
        ClientEntity::class,
        CeilingEntity::class,
        OrderEntity::class,
        MaterialEntity::class,
    ), version = 1, exportSchema = false
)

abstract class RoomDb : RoomDatabase() {

    abstract fun ClientDAO(): ClientDAO
    abstract fun CeilingDAO(): CeilingDAO
    abstract fun MaterialDAO(): MaterialsDAO
}