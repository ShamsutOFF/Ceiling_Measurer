package com.example.ceilingmeasurer.data.room.dao

import androidx.room.*
import com.example.ceilingmeasurer.data.room.tables.MaterialEntity
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Dao
interface MaterialsDAO {

    @Query("SELECT * FROM ${RoomConst.TABLE_MATERIALS}")
    fun getAllMaterials(): List<MaterialEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewMaterial(material: MaterialEntity)

    @Update
    fun updateMaterial(material: MaterialEntity)

    @Delete
    fun deleteMaterial(material: MaterialEntity)
}