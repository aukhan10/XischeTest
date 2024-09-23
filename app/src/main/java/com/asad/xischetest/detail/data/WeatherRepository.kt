package com.asad.xischetest.detail.data

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val dataSource: WeatherDataSource) {
    suspend fun getWeatherData(cityName: String) = dataSource.getWeatherData(cityName)
}