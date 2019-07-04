package com.jskim.weather

import com.jskim.weather.di.baseUrl
import com.jskim.weather.model.ConsolidatedWeather
import com.jskim.weather.model.Weather
import com.jskim.weather.util.formatYMD
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val list = mutableListOf<Weather>().apply {
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-28"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Seoul"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Heavy Cloud", "hc", 4.toDouble(), 4, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "San Jose"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Heavy Cloud", "hc", 6.toDouble(), 6, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Brussels"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Showers", "s", 8.toDouble(), 8, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Toulouse"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Light Cloud", "lc", 2.toDouble(), 3, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Sendai"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Light Rain", "lc", 11.toDouble(), 11, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Southend-on-Sea"))
            add(Weather(mutableListOf<ConsolidatedWeather>().apply {
                add(ConsolidatedWeather("Showers", "s", 13.toDouble(), 13, "2019-06-25"))
                add(ConsolidatedWeather("Light Rain", "lr", 2.toDouble(), 2, "2019-06-26"))
            }, "Swansea"))
        }


        println("************** Before *************")
        println("list: ${list.joinToString("\n")}")
        println("************** After *************")

//        Observable.fromIterable(list)
//            .map {
//                extractDisplayDate(it)
//            }
//            .subscribe {
//                println(it)
//            }


    }

    val todayDateString = Date().formatYMD()

    val tomorrowDateString = Calendar.getInstance().run {
        add(Calendar.DATE, 1)
        time
    }.formatYMD()


    @Test
    fun apiTest() {
        val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    })
                    .build()
            )
            .build()
            .create(Api::class.java)

        api.getLocationSearch("se")
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it }
            .flatMap {
                api.getLocation(it.woeid.toString()).toObservable()
            }
            .toList()
            .subscribe({ response ->
                println("response: $response")

            }, { errorResponse ->
                println("onError ${errorResponse.message}")

            })
        Thread.sleep(30000)
    }
}
