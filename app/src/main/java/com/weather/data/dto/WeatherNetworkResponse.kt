package com.weather.data.dto


import com.google.gson.annotations.SerializedName

data class WeatherNetworkResponse(

    @SerializedName("base")
    val base: String,

    @SerializedName("cod")
    val cod: Int,

    @SerializedName("coord")
    val coordinate: LocationData,

    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val main: MainData,

    @SerializedName("name")
    val name: String,

    @SerializedName("sys")
    val countryInfo: CountryInfoData,

    @SerializedName("visibility")
    val visibility: Int,

    @SerializedName("weather")
    val weather: List<WeatherData>,

    @SerializedName("wind")
    val wind: WindData
)