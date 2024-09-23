package com.asad.xischetest.home

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("error") var error: Boolean? = null,
    @SerializedName("msg") var msg: String? = null,
    @SerializedName("data") var data: ArrayList<Country> = arrayListOf()
)

data class Country(

    @SerializedName("iso2") var iso2: String? = null,
    @SerializedName("iso3") var iso3: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("cities") var cities: ArrayList<String> = arrayListOf()

)