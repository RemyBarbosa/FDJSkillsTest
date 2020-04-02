package com.rocketskill.interface_adapter.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rocketskill.interface_adapter.base.ErrorState
import com.rocketskill.interface_adapter.base.LoadingState
import com.rocketskill.interface_adapter.base.RxViewModel
import com.rocketskill.interface_adapter.base.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.rocketskill.interface_adapter.weather.model.WeatherUIModel

class HourlyWeatherViewModel(
    private val weatherManager: WeatherManager
) : RxViewModel() {

    private val mStates = MutableLiveData<State>()
    val states: LiveData<State>
        get() = mStates

    fun observeHourlyWeatherList(
        latitude: Float,
        longitude: Float,
        count: Int,
        units: String,
        appId: String
    ) {
        mStates.value = LoadingState
        launch {
            weatherManager.getHourlyWeatherList(latitude, longitude, count, units, appId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(this::onDailyWeatherListReceive, this::onError)
        }
    }

    private fun onDailyWeatherListReceive(weatherUiModelList: List<WeatherUIModel>) {
        mStates.value = HourlyWeatherListState(weatherUiModelList)
    }

    private fun onError(error: Throwable) {
        mStates.value = ErrorState(error)
    }

    data class HourlyWeatherListState(val weatherUIModelList: List<WeatherUIModel>) : State()
}