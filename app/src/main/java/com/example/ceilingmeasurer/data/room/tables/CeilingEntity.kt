package com.example.ceilingmeasurer.data.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Entity(tableName = RoomConst.TABLE_CEILINGS)
data class CeilingEntity(

    @PrimaryKey(autoGenerate = true) val ceilingId: Int,
    val clientId: Int,
    val name: String,
    val name_material: String,
    val length: Double,
    val width: Double,
    val chandeliers: Int,
    val lamps: Int,
    val corners: Int,
    val stroke: Int,
    val two_steps: Double,
    val curtain: Double,
    val alu_curtain: Double,
    val price_for_m2: Double,
    val attachment: String,
)
