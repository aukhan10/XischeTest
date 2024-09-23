package com.asad.xischetest.detail.data

import com.asad.xischetest.detail.WeatherResponse
import com.asad.xischetest.network.WeatherApi

class WeatherDataSourceImpl(
    private val weatherApi: WeatherApi
) : WeatherDataSource {
    override suspend fun getWeatherData(cityName: String): WeatherResponse {
        return weatherApi.getWeatherData(city = cityName).body()!!
    }
}