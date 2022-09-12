package com.example.ceilingmeasurer.model

import androidx.room.*
import com.example.ceilingmeasurer.model.Tables.ClientEntity

@Dao
interface ClientsDAO {

    @Query("SELECT * FROM ClientEntity")
    fun all(): List<ClientEntity>

    @Query("SELECT * FROM ClientEntity WHERE name LIKE :name")
    fun getDataName(name: String): List<ClientEntity>

    @Query("SELECT * FROM ClientEntity WHERE surname LIKE :surname")
    fun getDataSurname(surname: String): List<ClientEntity>

    @Query("SELECT * FROM ClientEntity WHERE phone_number LIKE :phone_number")
    fun getDataPhoneNumber(phone_number: String): List<ClientEntity>

    @Query("SELECT * FROM ClientEntity WHERE address LIKE :address")
    fun getDataAddress(address: String): List<ClientEntity>

    @Query("SELECT * FROM ClientEntity WHERE district LIKE :district")
    fun getDataDistrict(district: String): List<ClientEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: ClientEntity)

    @Update
    fun update(entity: ClientEntity)

    @Delete
    fun delete(entity: ClientEntity)

}