package com.example.hellolibrary.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.hellolibrary.data.entities.Message

@Dao
interface MessageDao {

@Query("SELECT * FROM message_table")
    fun getAll(): List<Message>
}