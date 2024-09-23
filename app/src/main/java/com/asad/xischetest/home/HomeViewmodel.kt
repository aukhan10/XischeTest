package com.asad.xischetest.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.xischetest.home.usecase.GetAllCitiesUseCase
import com.asad.xischetest.home.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getCountries() {
        viewModelScope.launch {
            val countries = getCountriesUseCase()
            val cities = getAllCitiesUseCase()
            _uiState.update { currentState ->
                currentState.copy(
                    countryList = countries,
                    cityList = cities
                )
            }
        }
    }
}

data class HomeUiState(
    val countryList: List<Country> = mutableListOf(),
    val cityList: List<String> = mutableListOf(),
)