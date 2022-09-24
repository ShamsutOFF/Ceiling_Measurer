package com.example.ceilingmeasurer.data.room.dao

import androidx.room.*
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Dao
interface CeilingDAO {

    @Query("SELECT * FROM ${RoomConst.TABLE_CEILINGS} WHERE clientId LIKE :clientId")
    fun getAllCeilingsByClientId(clientId: Int): List<CeilingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewCeiling(entity: CeilingEntity)

    @Update
    fun updateCeiling(entity: CeilingEntity)

    @Delete
    fun deleteCeiling(entity: CeilingEntity)

    @Query("DELETE FROM ${RoomConst.TABLE_CEILINGS} WHERE clientId = :id")
    fun deleteCeilingByClientId(id: Int)
}