package com.jskim.weather

import android.app.Application
import com.jskim.weather.di.apiModule
import com.jskim.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@BaseApplication)
            modules(listOf(apiModule, weatherModule))
        }
    }
}