package com.asad.xischetest.home.usecase

import com.asad.xischetest.home.data.CountriesRepository
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(private val repository: CountriesRepository) {
    suspend operator fun invoke(): List<String> {
        return repository.getCountries().flatMap { it.cities }
    }
}