package com.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.data.dto.WeatherEntity
import androidx.room.Update

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherLocalData: WeatherEntity)

    @Query("SELECT * FROM tb_weather where city_id = :cityId LIMIT 1")
    suspend fun getWeatherByID(cityId: Int): WeatherEntity?

    @Query("SELECT * FROM tb_weather where city_name = :cityName LIMIT 1")
    suspend fun getWeatherByName(cityName: String): WeatherEntity?

    @Query("DELETE FROM tb_weather")
    suspend fun deleteAllWeather()

    @Update
    suspend fun updateFav(weatherLocalData: WeatherEntity)

}
