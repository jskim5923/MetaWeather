package com.jskim.weather.util

import java.text.SimpleDateFormat
import java.util.*

fun Date?.formatYMD(): String {
    if (this == null) {
        return ""
    }
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)
}