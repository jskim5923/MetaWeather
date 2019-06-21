package com.jskim.idus.idus_codingtest.model

data class ConsolidatedWeather(
        val weather_state_name: String,
        val weather_state_abbr: String,
        val the_temp: Double,
        val humidity: Int,
        val applicable_date:String
)