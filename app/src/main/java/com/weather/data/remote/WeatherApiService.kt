package com.weather.data.remote

import com.weather.data.dto.WeatherNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    /**
     * This function gets the Weather for the [location] the
     * user searched for.
     */
    @GET("/data/2.5/weather")
    suspend fun getSpecificWeather(
        @Query("q") location: String,
        @Query("units") unitSystem:String,
    ): Response<WeatherNetworkResponse>

    // This function gets the weather information for the user's location.
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unitSystem:String,
    ): Response<WeatherNetworkResponse>

}
