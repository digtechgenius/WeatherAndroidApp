package com.weather.data.dto

import com.google.gson.annotations.SerializedName


data class MainData(

    @SerializedName("humidity")
    val humidity: String,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    @SerializedName("temp_min")
    val tempMin: Double
)

data class WeatherData(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)

data class WindData(

    @SerializedName("deg")
    val deg: Int,

    @SerializedName("speed")
    val speed: String
)

data class CountryInfoData(

    @SerializedName("country")
    val country: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long
)

data class LocationData(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

