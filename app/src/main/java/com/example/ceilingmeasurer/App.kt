package com.example.ceilingmeasurer

import android.app.Application
import androidx.room.Room
import com.example.ceilingmeasurer.data.room.DataBaseApp
import com.example.ceilingmeasurer.data.room.dao.ClientsDAO
import timber.log.Timber
import timber.log.Timber.Forest.plant


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        plant(Timber.DebugTree())
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