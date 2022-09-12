package com.example.ceilingmeasurer.data.room.dao

import androidx.room.*
import com.example.ceilingmeasurer.data.room.tables.PriceWorksAndMaterialsEntity

@Dao
interface PriceWorksAndMaterialsDAO {

    @Query ("SELECT * FROM PriceWorksAndMaterialsEntity")
    fun allPrice(): List<PriceWorksAndMaterialsEntity>

}