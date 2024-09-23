package com.asad.xischetest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val WEATHER_URL = "https://api.weatherapi.com/"
    private const val COUNTRIES_URL = "https://countriesnow.space/"

    fun getInstance(isCountry: Boolean): Retrofit {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder =
            client.newBuilder().addInterceptor(interceptor)

        return Retrofit.Builder().baseUrl(getUrl(isCountry))
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    private fun getUrl(isCountry: Boolean): String =
        if (isCountry) COUNTRIES_URL else WEATHER_URL
}