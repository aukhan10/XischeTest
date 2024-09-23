package com.asad.xischetest.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.xischetest.detail.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            val weather = getWeatherUseCase(cityName)
            _uiState.update { currentState ->
                currentState.copy(
                    cityName = weather.location?.name.orEmpty(),
                    currentTemperature = weather.current?.tempC?.toInt().toString(),
                    condition = weather.current?.condition?.text.orEmpty(),
                    forecastDayList = weather.forecast?.forecastday?.toList().orEmpty()
                )
            }
        }
    }
}

data class WeatherUiState(
    val cityName: String = "",
    val currentTemperature: String = "",
    val condition : String = "",
    val forecastDayList: List<ForecastDay> = mutableListOf()
)