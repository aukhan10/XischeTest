package com.asad.xischetest.home.data

import com.asad.xischetest.home.Country

interface CountriesDataSource {
    suspend fun getCountries() : List<Country>
}