package com.asad.xischetest.network

import com.asad.xischetest.home.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {
    @GET("/api/v0.1/countries")
    suspend fun getCountries() : Response<CountryResponse>
}