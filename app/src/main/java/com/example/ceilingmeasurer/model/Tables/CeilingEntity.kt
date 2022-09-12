package com.example.ceilingmeasurer.model.Tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CeilingEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name_material: String,
    val square: Double,
    val perimeter: Double,
    val chandeliers: Int,
    val lamps: Int,
    val corners: Int,
    val stroke: Int,
    val two_steps: Double,
    val curtain: Double,
    val alu_curtain: Double,
    val price_for_m2: Double,
    val attachment: String,
    val drawing: String
)
