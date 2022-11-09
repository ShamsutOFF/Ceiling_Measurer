package com.example.hellolibrary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hellolibrary.data.entities.Message

@Dao
interface MessageDao {

    @Query("SELECT * FROM message_table")
    fun getAll(): List<Message>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMessage(message: Message)

    @Query("DELETE FROM message_table")
    fun deleteAll()

    @Query("SELECT * FROM message_table LIMIT 1")
    fun getFirst() : Message?

    @Query("DELETE FROM message_table WHERE message = :message")
    fun deleteMessage(message: String)

}