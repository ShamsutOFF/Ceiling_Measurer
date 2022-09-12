package com.example.ceilingmeasurer

import android.app.Application
import androidx.room.Room
import com.example.ceilingmeasurer.model.ClientsDAO
import com.example.ceilingmeasurer.model.DataBaseApp

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: DataBaseApp? = null
        private const val DB_NAME = "DataBaseApp.db"

        fun getClientsDao(): ClientsDAO {

            if(db == null) {
                synchronized(DataBaseApp::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                DataBaseApp::class.java,
                                DB_NAME
                            ).allowMainThreadQueries()
                                .build()
                        } ?: throw Exception ("WHAT IS?")
                    }
                }
            }

            return db!!.ClientsDAO()
        }
    }
}