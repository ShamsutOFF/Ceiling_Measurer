package com.example.ceilingmeasurer.data.room.dao

import androidx.room.*
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity

@Dao
interface CeilingDAO {

    @Query("SELECT * FROM CeilingEntity")
    fun allCeiling(): List<CeilingEntity>

}