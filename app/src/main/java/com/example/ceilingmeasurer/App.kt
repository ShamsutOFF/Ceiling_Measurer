package com.example.ceilingmeasurer

import android.app.Application
import androidx.room.Room
import com.example.ceilingmeasurer.data.room.DataBaseApp
import com.example.ceilingmeasurer.data.room.dao.ClientsDAO
import com.example.ceilingmeasurer.di.KoinModules.repository
import com.example.ceilingmeasurer.di.KoinModules.repositoryMaterial
import com.example.ceilingmeasurer.di.KoinModules.viewModel
import com.example.ceilingmeasurer.di.KoinModules.viewModelMaterial
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        this.also { appInstance = it }

        startKoin {
            androidContext(this@App)
            modules(listOf(repository, viewModel, repositoryMaterial, viewModelMaterial))
        }
    }

    companion object {
        private var appInstance: App? = null
        private var db: DataBaseApp? = null
        private const val DB_NAME = "DataBaseApp.db"

        fun getClientsDao(): ClientsDAO {

            if (db == null) {
                synchronized(DataBaseApp::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                DataBaseApp::class.java,
                                DB_NAME
                            ).allowMainThreadQueries()
                                .build()
                        } ?: throw Exception("WHAT IS?")
                    }
                }
            }

            return db!!.ClientsDAO()
        }
    }
}