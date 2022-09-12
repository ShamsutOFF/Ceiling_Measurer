package com.example.ceilingmeasurer.model

import androidx.room.*
import com.example.ceilingmeasurer.model.Tables.PriceWorksAndMaterialsEntity

@Dao
interface PriceWorksAndMaterialsDAO {

    @Query ("SELECT * FROM PriceWorksAndMaterialsEntity")
    fun allPriceWorks(): List<PriceWorksAndMaterialsEntity>

}