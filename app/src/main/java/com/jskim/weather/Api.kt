package com.jskim.weather

import com.jskim.weather.model.LocationSearch
import com.jskim.weather.model.Weather
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