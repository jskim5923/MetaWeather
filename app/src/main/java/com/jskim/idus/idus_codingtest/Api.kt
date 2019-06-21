package com.jskim.idus.idus_codingtest

import com.jskim.idus.idus_codingtest.model.Location
import com.jskim.idus.idus_codingtest.model.LocationSearch
import com.jskim.idus.idus_codingtest.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("api/location/search/")
    fun getLocationSearch(@Query("query") query: String): Single<List<LocationSearch>>

    @GET("api/location/{woeId}")
    fun getLocation(@Path("woeId") woeId: String): Single<Weather>

}