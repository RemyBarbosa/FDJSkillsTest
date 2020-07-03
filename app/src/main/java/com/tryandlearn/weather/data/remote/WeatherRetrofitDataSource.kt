package com.tryandlearn.weather.data.remote

import com.tryandlearn.interface_adapter.weather.model.WeatherRemoteList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherRetrofitDataSource {

    @GET("forecast/daily")
    fun getDailyWeatherList(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("cnt") count: Int,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Flowable<WeatherRemoteList>

    @GET("forecast/hourly")
    fun getHourlyWeatherList(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("cnt") count: Int,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Flowable<WeatherRemoteList>
}
