package com.weather.data.dto

import com.weather.utils.WeatherFormattingUtils
import javax.inject.Inject

class WeatherEntityMapper @Inject constructor() {

    fun weatherResponseToWeatherEntity(
        weatherNetworkResponse: WeatherNetworkResponse
    ): WeatherEntity {

        return WeatherEntity(
            name = weatherNetworkResponse.name,
            cityId = weatherNetworkResponse.id,
            updatedDate = WeatherFormattingUtils.convertTimestampToDate(
                weatherNetworkResponse.timestamp
            ),
            description = weatherNetworkResponse.weather[0].description,
            temperature = WeatherFormattingUtils.getTemp(
                weatherNetworkResponse.main.temp
            ),
            minTemperature = WeatherFormattingUtils.getMinTemp(
                weatherNetworkResponse.main.tempMin
            ),
            maxTemperature = WeatherFormattingUtils.getMaxTemp(
                weatherNetworkResponse.main.tempMax
            ),
            sunrise = WeatherFormattingUtils.getHourAndMinute(
                weatherNetworkResponse.countryInfo.sunrise
            ),
            sunset = WeatherFormattingUtils.getHourAndMinute(
                weatherNetworkResponse.countryInfo.sunset
            ),
            windSpeed = WeatherFormattingUtils.getWindSpeed(
                weatherNetworkResponse.wind.speed
            ),
            pressure = WeatherFormattingUtils.getPressure(
                weatherNetworkResponse.main.pressure
            ),
            feelsLike = WeatherFormattingUtils.getFeelTemp(
                weatherNetworkResponse.main.feelsLike
            ),
            humidity = WeatherFormattingUtils.getHumidity(
                weatherNetworkResponse.main.humidity
            )
        )
    }
}