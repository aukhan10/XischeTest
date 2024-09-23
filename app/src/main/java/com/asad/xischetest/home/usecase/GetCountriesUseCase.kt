package com.asad.xischetest.home.usecase

import com.asad.xischetest.home.data.CountriesRepository
import com.asad.xischetest.home.Country
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountriesRepository) {
    suspend operator fun invoke(): List<Country> {
        return repository.getCountries()
    }
}