package com.weather.utils

import android.text.format.DateFormat
import timber.log.Timber
import java.util.*


object WeatherFormattingUtils {

    fun convertTimestampToDate(timestamp: Long): String {
        var dateShown = ""
        try {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.timeInMillis = timestamp * 1000
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val date = DateFormat.format("dd-MM-yyyy HH:mm", calendar).toString()
            dateShown = "Updated at: $date"
        } catch (ex: Exception) {
            Timber.tag("Date parse error")
        }
        return dateShown
    }

    fun getHourAndMinute(timestamp: Long): String {
        var timeShown = ""
        try {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.timeInMillis = timestamp * 1000
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            timeShown = "${hour}:${minute}"
        } catch (ex: Exception) {
            Timber.tag("time parse error")
        }
        return timeShown
    }

    fun getTemp(temp: Double): String {
        return "$temp ℃"
    }

    fun getMinTemp(temp: Double): String {
        return "$temp ℃"
    }

    fun getMaxTemp(temp: Double): String {
        return "$temp ℃"
    }

    fun getFeelTemp(temp: Double): String {
        return "$temp ℃"
    }

    fun getWindSpeed(speed: String): String {
        return "$speed m/s"
    }

    fun getPressure(pressure: Int): String {
        return "$pressure hPa"
    }

    fun getHumidity(pressure: String): String {
        return "$pressure %"
    }
}