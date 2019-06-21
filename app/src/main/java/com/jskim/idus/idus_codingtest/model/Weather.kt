package com.jskim.idus.idus_codingtest.model

data class Weather(
    val consolidated_weather: List<ConsolidatedWeather>,
    val title: String
)
