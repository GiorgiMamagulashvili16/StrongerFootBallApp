package com.example.strongerfootballapp.app

import android.app.Application
import com.example.strongerfootballapp.di.mappersModule
import com.example.strongerfootballapp.di.networkModule
import com.example.strongerfootballapp.di.resourcesModule
import com.example.strongerfootballapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FootballApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FootballApp)
            modules(listOf(networkModule, viewModelModule, mappersModule, resourcesModule))
        }
    }
}