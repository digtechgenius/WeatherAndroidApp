package com.weather.data.repository

import android.content.Context
import androidx.preference.PreferenceManager
import com.weather.data.dto.FavCities
import com.weather.data.dto.LocationData
import com.weather.data.dto.WeatherEntity
import com.weather.data.dto.WeatherEntityMapper
import com.weather.data.local.FavCitiesDao
import com.weather.data.local.WeatherDao
import com.weather.data.remote.WeatherApiService
import com.weather.utils.AppConstants.STANDARD_ERROR_MESSAGE
import com.weather.utils.AppConstants.sessionLocalWeatherValid
import com.weather.utils.DataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherApiService,
    private val localDataSource: WeatherDao,
    private val favDataSource: FavCitiesDao,
    private val currentWeatherMapper: WeatherEntityMapper,
    private val context: Context,
) {
    private lateinit var weatherCity: WeatherEntity
    private lateinit var favCities: FavCities

    private fun isWeatherInitialised() = ::weatherCity.isInitialized
    private fun isFavInitialised() = ::favCities.isInitialized

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    suspend fun getLocalWeather(location: LocationData): Flow<DataResponse<WeatherEntity>> {
        return flow {
            emit(DataResponse.Loading)
            try {
                if (sessionLocalWeatherValid) { // reduce repeated call by practical session management
                    localWeatherCachedResult()
                } else { // regular flow
                    val response =
                        remoteDataSource.getCurrentWeather(location.lat, location.lon, "Metric")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val weatherEntity =
                                currentWeatherMapper.weatherResponseToWeatherEntity(it)
                            saveCurrentLocationId(weatherEntity.cityId) // location cache
                            weatherCity = weatherEntity
                            emit(DataResponse.Success((weatherEntity)))
                            storeWeatherData(weatherEntity)
                            sessionLocalWeatherValid = true
                        }
                    } else {
                        localWeatherCachedResult() // failure case
                    }
                }
            } catch(ex: Exception){ // boundary case
                localWeatherCachedResult()
            }
        }
    }

    private suspend fun FlowCollector<DataResponse<WeatherEntity>>.localWeatherCachedResult() {
        val localWeather = localDataSource.getWeatherByID(getCurrentLocationId())
        if (localWeather != null) {
            weatherCity = localWeather
            emit(DataResponse.Cache((localWeather)))
        } else {
            emit(DataResponse.Error(STANDARD_ERROR_MESSAGE))
        }
    }

    private suspend fun storeWeatherData(weatherEntity: WeatherEntity) {
        localDataSource.insertWeather(weatherEntity)
    }

    suspend fun searchWeather(location: String): Flow<DataResponse<WeatherEntity>> {
        return flow {
            emit(DataResponse.Loading)
            try {
                if (isWeatherInitialised() && weatherCity.name == location) { // reduce repeated call
                    searchWeatherCachedResult(location)
                } else { // regular flow
                    val response = remoteDataSource.getSpecificWeather(location, "Metric")
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val weatherEntity =
                                currentWeatherMapper.weatherResponseToWeatherEntity(it)
                            storeWeatherData(weatherEntity)
                            weatherCity = weatherEntity
                            emit(DataResponse.Success((weatherEntity)))
                        }
                    } else {  // failure case
                        searchWeatherCachedResult(location)
                    }
                }
            } catch (ex: Exception) {  // boundary case
                searchWeatherCachedResult(location)
            }
        }
    }

    private suspend fun FlowCollector<DataResponse<WeatherEntity>>.searchWeatherCachedResult(
        location: String
    ) {
        val localWeather = localDataSource.getWeatherByName(location)
        if (localWeather != null) {
            weatherCity = localWeather
            emit(DataResponse.Cache((localWeather)))
        } else {
            emit(DataResponse.Error(STANDARD_ERROR_MESSAGE))
        }
    }

    private fun saveCurrentLocationId(id: Int) {
        prefs.edit().putInt("city_id", id).apply()
    }

    private fun getCurrentLocationId(): Int {
        return prefs.getInt("city_id", -1)
    }

    suspend fun addFavCity() {
        if (isWeatherInitialised()) { // rare boundary case if there is no session value then nothing to add
            if (!isFavInitialised()) { // means city does not exist in fav DB. So add it as fav first time
                favCities =
                    FavCities(cityId = weatherCity.cityId, cityName = weatherCity.name, fav = true)
            } else { // toggle fav
                favCities.fav = !favCities.fav
            }
            favDataSource.insertFavCity(favCities)
        }
    }

    suspend fun getFavCities(): Flow<DataResponse<List<FavCities>>> {
        return flow {
            emit(DataResponse.Loading)
            try {
                emit(DataResponse.Success((favDataSource.getAllFav())))
            } catch (ex: Exception) {
                emit(DataResponse.Error(STANDARD_ERROR_MESSAGE))
            }
        }
    }

    suspend fun getFavCityID(): Flow<FavCities?> {
        return flow {
            emit(favDataSource.getFavCityByID(weatherCity.cityId))
        }
    }
}
