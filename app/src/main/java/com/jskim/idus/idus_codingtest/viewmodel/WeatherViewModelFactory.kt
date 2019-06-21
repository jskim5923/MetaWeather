package com.jskim.idus.idus_codingtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jskim.idus.idus_codingtest.model.Repository

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}