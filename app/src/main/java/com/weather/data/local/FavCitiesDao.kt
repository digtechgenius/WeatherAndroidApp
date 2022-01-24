package com.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.weather.data.dto.FavCities


@Dao
interface FavCitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCity(favCities: FavCities)

    @Query("SELECT * FROM tb_fav where city_id = :cityId LIMIT 1")
    suspend fun getFavCityByID(cityId: Int): FavCities?

    @Query("SELECT * FROM tb_fav where fav == 1")
    suspend fun getAllFav(): List<FavCities>

    @Query("DELETE FROM tb_fav where city_id = :cityId")
    suspend fun deleteFav(cityId: Int)

}
