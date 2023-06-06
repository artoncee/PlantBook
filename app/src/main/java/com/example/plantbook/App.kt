package com.example.plantbook

import android.app.Application
import com.example.plantbook.di.presenterModule
import com.example.plantbook.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(presenterModule, utilsModule))
        }
    }
}