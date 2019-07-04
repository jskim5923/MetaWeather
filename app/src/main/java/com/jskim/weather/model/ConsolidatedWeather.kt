package com.jskim.weather.model

data class ConsolidatedWeather @JvmOverloads constructor(
    var weather_state_name: String = "",
    var weather_state_abbr: String = "",
    var the_temp: Double = 0.0,
    var humidity: Int = 0,
    var applicable_date: String = ""
)