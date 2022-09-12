package com.example.ceilingmeasurer.model

import androidx.room.*
import com.example.ceilingmeasurer.model.Tables.CeilingEntity

@Dao
interface CeilingDAO {

    @Query("SELECT * FROM CeilingEntity")
    fun allCeiling(): List<CeilingEntity>

}