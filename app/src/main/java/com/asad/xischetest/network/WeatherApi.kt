package com.asad.xischetest.network

import com.asad.xischetest.detail.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String = "f592dac2f9664c84833103112242109",
        @Query("q") city: String,
        @Query("days") days: Int = 5,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Response<WeatherResponse>
}