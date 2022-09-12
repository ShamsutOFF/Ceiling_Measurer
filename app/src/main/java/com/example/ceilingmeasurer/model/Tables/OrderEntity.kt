package com.example.ceilingmeasurer.model.Tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val id_client: String,
    val status: String,
    val date_timestamp: Long,
    val date_work: String,
    val ceilings: String,
    val description: String,
    val attachment: String
)
