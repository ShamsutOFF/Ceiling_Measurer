package com.example.ceilingmeasurer.data.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Entity(tableName = RoomConst.TABLE_CLIENTS)
data class ClientEntity(

    @PrimaryKey(autoGenerate = true) val clientId: Int = 0,
    val name: String,
    val surname: String,
    val phone_number: String,
    val address: String,
    val district: String,
    val status: String
)
