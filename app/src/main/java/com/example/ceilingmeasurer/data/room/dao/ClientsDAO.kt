package com.example.ceilingmeasurer.data.room.dao

import androidx.room.*
import com.example.ceilingmeasurer.data.room.tables.ClientEntity
import com.example.ceilingmeasurer.data.room.utils.RoomConst

@Dao
interface ClientDAO {

    @Query("SELECT * FROM ${RoomConst.TABLE_CLIENTS}")
    fun getAllClients(): List<ClientEntity>

    @Query("SELECT * FROM ${RoomConst.TABLE_CLIENTS} WHERE clientId LIKE :id")
    fun getClientById(id: Int): List<ClientEntity>

    @Query("SELECT * FROM ${RoomConst.TABLE_CLIENTS} WHERE name LIKE :name")
    fun getClientByName(name: String): List<ClientEntity>

    @Query("SELECT * FROM ${RoomConst.TABLE_CLIENTS} WHERE surname LIKE :surname")
    fun getClientBySurname(surname: String): List<ClientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewClient(entity: ClientEntity)

    @Update
    fun updateClient(entity: ClientEntity)

    @Delete
    fun deleteClient(entity: ClientEntity)
}