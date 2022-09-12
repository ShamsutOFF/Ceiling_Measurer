package com.example.ceilingmeasurer.model.Tables

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "price_materials")
data class PriceMaterialsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name_material: String,
    val unit_measure: String,
    val unit_price: Double
)