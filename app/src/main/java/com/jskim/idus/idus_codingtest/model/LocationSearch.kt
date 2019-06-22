package com.jskim.idus.idus_codingtest.model

data class LocationSearch(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)