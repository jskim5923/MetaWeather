package com.jskim.idus.idus_codingtest.model

import com.jskim.idus.idus_codingtest.Api
import io.reactivex.Single

class RemoteRepositoryImpl(private val api: Api) : Repository {
    override fun getLocationSearch(query: String): Single<List<LocationSearch>> {
        return api.getLocationSearch(query)
    }

    override fun getLocation(woeId: String): Single<Weather> {
        return api.getLocation(woeId)
    }
}