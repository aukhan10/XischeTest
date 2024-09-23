package com.asad.xischetest.detail.data

import com.asad.xischetest.detail.WeatherResponse

interface WeatherDataSource {
    suspend fun getWeatherData(cityName: String) : WeatherResponse
}