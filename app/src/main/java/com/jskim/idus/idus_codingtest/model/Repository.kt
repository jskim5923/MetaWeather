package com.jskim.idus.idus_codingtest.model

import io.reactivex.Single

interface Repository {
    fun getLocationSearch(query: String): Single<List<LocationSearch>>

    fun getLocation(woeId: String): Single<Weather>
}