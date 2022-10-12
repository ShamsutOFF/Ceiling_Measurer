package com.example.hellolibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hellolibrary.data.entities.Message

@Database(entities = [Message::class], version = 1)
abstract class MessageDatabase : RoomDatabase() {
}