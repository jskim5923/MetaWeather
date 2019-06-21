package com.jskim.idus.idus_codingtest

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_search.base.BaseActivity
import com.jskim.idus.idus_codingtest.adapter.WeatherRecyclerAdapter
import com.jskim.idus.idus_codingtest.databinding.ActivityMainBinding
import com.jskim.idus.idus_codingtest.model.ConsolidatedWeather
import com.jskim.idus.idus_codingtest.model.Weather
import com.jskim.idus.idus_codingtest.viewmodel.WeatherViewModel
import com.jskim.idus.idus_codingtest.viewmodel.WeatherViewModelFactory
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    private lateinit var viewModel: WeatherViewModel

    private val weatherViewModelFactory: WeatherViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        viewModel.getWeatherList()

    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, weatherViewModelFactory).get(WeatherViewModel::class.java)
        dataBinding.run {
            lifecycleOwner = this@MainActivity
            this.viewModel = this@MainActivity.viewModel
            dataBinding.recyclerView.apply {
                adapter = WeatherRecyclerAdapter()
            }
        }
    }
}
