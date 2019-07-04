package com.jskim.weather.model

data class LocationSearch(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)