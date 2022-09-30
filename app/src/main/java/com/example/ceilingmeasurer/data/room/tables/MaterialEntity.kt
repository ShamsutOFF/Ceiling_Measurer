package com.example.ceilingmeasurer.data.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Entity(tableName = RoomConst.TABLE_MATERIALS)
data class MaterialEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name_material: String,
    val unit_measure: String,
    val unit_price: Double,
    val unit_work_price: Double
)
