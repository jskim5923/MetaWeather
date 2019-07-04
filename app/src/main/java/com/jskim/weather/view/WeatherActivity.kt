package com.jskim.weather.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.movie_search.base.BaseActivity
import com.jskim.weather.R
import com.jskim.weather.adapter.WeatherRecyclerAdapter
import com.jskim.weather.adapter.decoration.BackgroundDecoration
import com.jskim.weather.adapter.decoration.DividerDecoration
import com.jskim.weather.databinding.ActivityWeatherBinding
import com.jskim.weather.viewmodel.WeatherViewModel
import com.jskim.weather.viewmodel.WeatherViewModelFactory
import org.koin.android.ext.android.inject

class WeatherActivity : BaseActivity<ActivityWeatherBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_weather

    private lateinit var viewModel: WeatherViewModel

    private val weatherViewModelFactory: WeatherViewModelFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, weatherViewModelFactory).get(WeatherViewModel::class.java)
        dataBinding.run {
            lifecycleOwner = this@WeatherActivity
            this.viewModel = this@WeatherActivity.viewModel
            dataBinding.recyclerView.apply {
                adapter = WeatherRecyclerAdapter()
                addItemDecoration(BackgroundDecoration(this@WeatherActivity))
                addItemDecoration(DividerDecoration(this@WeatherActivity))
            }
        }
    }
}
