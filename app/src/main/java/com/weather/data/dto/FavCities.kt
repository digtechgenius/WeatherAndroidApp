package com.weather.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_fav")
data class FavCities(

    @ColumnInfo(name = "city_id")
    @PrimaryKey
    val cityId: Int,

    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "fav")
    var fav: Boolean,

)