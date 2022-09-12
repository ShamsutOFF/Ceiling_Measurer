package com.example.ceilingmeasurer.data.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PriceWorksAndMaterialsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name_material: String,
    val unit_measure: String,
    val unit_price: Double,
    val unit_work_price: Double
)
