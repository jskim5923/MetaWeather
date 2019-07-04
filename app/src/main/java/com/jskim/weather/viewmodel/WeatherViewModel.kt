package com.jskim.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jskim.weather.base.DisposableViewModel
import com.jskim.weather.model.Repository
import com.jskim.weather.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(private val repository: Repository) : DisposableViewModel() {
    private val _weatherList = MutableLiveData<List<Weather>>()

    private val _showProgress = MutableLiveData<Boolean>()

    private val _showSwipeRefreshLayout = MutableLiveData<Boolean>()

    private val _swipeLayoutRefreshing = MutableLiveData<Boolean>()

    private val _networkErrorMessage = MutableLiveData<String>()

    private val _showNetworkErrorLayout = MutableLiveData<Boolean>()

    val weatherList: LiveData<List<Weather>>
        get() = _weatherList

    val showProgress: LiveData<Boolean>
        get() = _showProgress

    val showSwipeRefreshLayout: LiveData<Boolean>
        get() = _showSwipeRefreshLayout

    val swipeLayoutRefreshing: LiveData<Boolean>
        get() = _swipeLayoutRefreshing

    val networkErrorMessage: LiveData<String>
        get() = _networkErrorMessage

    val showNetworkErrorLayout: LiveData<Boolean>
        get() = _showNetworkErrorLayout

    init {
        _showProgress.value = true
        getWeatherList()
    }

    private fun getWeatherList() {
        addDisposable(
            repository.getLocationSearch("se")
                .subscribeOn(Schedulers.io())
                .flattenAsObservable { it }
                .flatMap {
                    repository.getLocation(it.woeid.toString()).toObservable()
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _weatherList.value = response
                    _showSwipeRefreshLayout.value = true
                    _swipeLayoutRefreshing.value = false
                    _showProgress.value = false
                }, { errorResponse ->
                    _showProgress.value = false
                    _showNetworkErrorLayout.value = true
                    _networkErrorMessage.value = errorResponse.message
                    _showSwipeRefreshLayout.value = false
                })
        )
    }

    fun swipeRefresh() {
        _swipeLayoutRefreshing.value = true
        refresh(false)

    }

    fun refresh(isShowProgress: Boolean) {
        _showNetworkErrorLayout.value = false
        _showProgress.value = isShowProgress
        getWeatherList()
    }
}