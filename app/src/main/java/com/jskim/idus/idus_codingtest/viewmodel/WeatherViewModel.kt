package com.jskim.idus.idus_codingtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_search.util.SingleLiveEvent
import com.jskim.idus.idus_codingtest.base.DisposableViewModel
import com.jskim.idus.idus_codingtest.model.Repository
import com.jskim.idus.idus_codingtest.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class WeatherViewModel(private val repository: Repository) : DisposableViewModel() {
    private val _weatherList = MutableLiveData<List<Weather>>()

    private val _showProgress = MutableLiveData<Boolean>()

    private val _isRefresh = SingleLiveEvent<Boolean>()

    private val _swipeLayoutRefreshing = MutableLiveData<Boolean>()

    private val _networkErrorMessage = MutableLiveData<String>()

    private val _showNetworkErrorLayout = MutableLiveData<Boolean>()

    val weatherList: LiveData<List<Weather>>
        get() = _weatherList

    val showProgress: LiveData<Boolean>
        get() = _showProgress

    val isRefresh: LiveData<Boolean>
        get() = _isRefresh


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

    fun getWeatherList() {
        val list = ArrayList<Weather>()
        addDisposable(
                repository.getLocationSearch("se")
                        .subscribeOn(Schedulers.io())
                        .flattenAsObservable { it }
                        .flatMap {
                            repository.getLocation(it.woeid.toString()).toObservable()
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            list.add(response)
                        }, { errorResponse ->
                            _showProgress.value = false
                            _showNetworkErrorLayout.value = true
                            if (errorResponse is HttpException) {
                                _networkErrorMessage.value = errorResponse.code().toString()
                            }
                        }, {
                            _weatherList.value = list
                            _swipeLayoutRefreshing.value = false
                            _showProgress.value = false

                        })
        )
    }

    fun swipeRefresh() {
        _isRefresh.call()
        _swipeLayoutRefreshing.value = true
        refresh(false)

    }

    fun refresh(isShowProgress:Boolean) {
        _showNetworkErrorLayout.value = false
        _showProgress.value = isShowProgress
        getWeatherList()
    }
}