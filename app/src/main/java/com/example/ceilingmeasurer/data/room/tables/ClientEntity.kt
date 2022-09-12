package com.example.ceilingmeasurer.data.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClientEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val surname: String,
    val phone_number: Long,
    val address: String,
    val district: String
)
