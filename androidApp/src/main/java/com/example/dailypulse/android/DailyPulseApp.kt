package com.example.dailypulse.android

import android.app.Application
import com.example.dailypulse.android.di.databaseModule
import com.example.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private  fun initKoin() {
        val modules = sharedKoinModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}