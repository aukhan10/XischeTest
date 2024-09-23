package com.asad.xischetest.home.data

import javax.inject.Inject

class CountriesRepository @Inject constructor(private val dataSource: CountriesDataSource) {
    suspend fun getCountries() = dataSource.getCountries()
}