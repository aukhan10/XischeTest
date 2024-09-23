package com.asad.xischetest.detail.usecase

import com.asad.xischetest.detail.WeatherResponse
import com.asad.xischetest.detail.data.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String): WeatherResponse {
        return repository.getWeatherData(cityName)
    }
}