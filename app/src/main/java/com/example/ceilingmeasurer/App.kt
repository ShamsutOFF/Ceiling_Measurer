package com.example.ceilingmeasurer

import android.app.Application
import com.example.ceilingmeasurer.di.KoinModules.repository
import com.example.ceilingmeasurer.di.KoinModules.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repository, viewModel))
        }
    }
}