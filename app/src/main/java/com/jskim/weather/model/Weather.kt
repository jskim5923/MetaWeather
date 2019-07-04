package com.jskim.weather.model

import com.jskim.weather.util.formatYMD
import java.util.*

data class Weather(
    val consolidated_weather: List<ConsolidatedWeather>,
    val title: String
) {
    private val todayDateString
        get() = Date().formatYMD()

    private val tomorrowDateString
        get() = Calendar.getInstance().run {
            add(Calendar.DATE, 1)
            time
        }.formatYMD()

    val todayConsolidatedWeather: ConsolidatedWeather
        get() = if (consolidated_weather.isNullOrEmpty()) {
            ConsolidatedWeather()
        } else {
            consolidated_weather.filter {
                it.applicable_date == todayDateString
            }.getOrElse(0) { ConsolidatedWeather() }
        }

    val tomorrowConsolidatedWeather: ConsolidatedWeather
        get() = if (consolidated_weather.isNullOrEmpty()) {
            ConsolidatedWeather()
        } else {
            consolidated_weather.filter {
                it.applicable_date == tomorrowDateString
            }.getOrElse(0) { ConsolidatedWeather() }
        }
}
