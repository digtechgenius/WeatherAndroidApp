package com.weather.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_weather")
data class WeatherEntity(
    @ColumnInfo(name = "city_name")
    val name:String,
    @ColumnInfo(name = "city_id")
    @PrimaryKey
    val cityId: Int,
    val updatedDate:String,
    val description:String,
    val temperature:String,
    val minTemperature:String,
    val maxTemperature:String,
    val sunrise:String,
    val sunset:String,
    val windSpeed:String,
    val pressure:String,
    val feelsLike:String,
    val humidity:String
)
