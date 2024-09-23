package com.asad.xischetest.di

import com.asad.xischetest.detail.data.WeatherDataSource
import com.asad.xischetest.detail.data.WeatherDataSourceImpl
import com.asad.xischetest.home.data.CountriesDataSource
import com.asad.xischetest.home.data.CountriesDataSourceImpl
import com.asad.xischetest.network.CountriesApi
import com.asad.xischetest.network.RetrofitHelper
import com.asad.xischetest.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideDataSource(api: CountriesApi): CountriesDataSource {
        return CountriesDataSourceImpl(api)
    }

    @Provides
    fun provideWeatherDataSource(api: WeatherApi): WeatherDataSource {
        return WeatherDataSourceImpl(api)
    }

    @Provides
    fun provideWeatherApi(): WeatherApi {
        return RetrofitHelper.getInstance(false).create(WeatherApi::class.java)
    }

    @Provides
    fun provideCountriesApi(): CountriesApi {
        return RetrofitHelper.getInstance(true).create(CountriesApi::class.java)
    }
}