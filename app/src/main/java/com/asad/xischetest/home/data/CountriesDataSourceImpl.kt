package com.asad.xischetest.home.data

import com.asad.xischetest.home.Country
import com.asad.xischetest.network.CountriesApi

class CountriesDataSourceImpl(
    private val countriesApi: CountriesApi
) : CountriesDataSource {
    override suspend fun getCountries(): List<Country> =
        countriesApi.getCountries().body()?.data?.toList().orEmpty()
}