package com.jskim.weather.di

import com.jskim.weather.model.RemoteRepositoryImpl
import com.jskim.weather.model.Repository
import com.jskim.weather.viewmodel.WeatherViewModelFactory
import org.koin.dsl.module

val weatherModule = module {
    factory {
        RemoteRepositoryImpl(get()) as Repository
    }

    factory {
        WeatherViewModelFactory(get())
    }
}