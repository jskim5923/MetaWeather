package com.jskim.idus.idus_codingtest.di

import com.jskim.idus.idus_codingtest.model.RemoteRepositoryImpl
import com.jskim.idus.idus_codingtest.model.Repository
import com.jskim.idus.idus_codingtest.viewmodel.WeatherViewModelFactory
import org.koin.dsl.module

val weatherModule = module {
    factory {
        RemoteRepositoryImpl(get()) as Repository
    }

    factory {
        WeatherViewModelFactory(get())
    }
}