package com.weather.ui.weatherDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.R
import com.weather.data.dto.FavCities
import com.weather.data.dto.LocationData
import com.weather.data.dto.WeatherEntity
import com.weather.data.repository.WeatherRepositoryImpl
import com.weather.ui.components.WeatherGridViewData
import com.weather.utils.Analytics
import com.weather.utils.AppConstants
import com.weather.utils.DataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel  @Inject constructor(
    private val repository: WeatherRepositoryImpl
): ViewModel() {

    private var weatherLiveData: MutableLiveData<DataResponse<WeatherEntity>> = MutableLiveData()

    val weatherLiveDataRead: LiveData<DataResponse<WeatherEntity>>
        get() = weatherLiveData


    val isFav = mutableStateOf(false)

    init {
        updateWeatherLiveData(DataResponse.Error("Please search weather"))
    }

    fun getCurrentWeather() {
        val location =
            LocationData(AppConstants.location.latitude, AppConstants.location.longitude)
        viewModelScope.launch {
            repository.getLocalWeather(location).collect {
                updateWeatherLiveData(it)
            }
        }
    }

    fun searchWeather(location: String) {
        Analytics.logEvents("City", location) // Demo purpose
        viewModelScope.launch {
            repository.searchWeather(location).collect {
                updateWeatherLiveData(it)
            }
        }
    }


    private fun updateWeatherLiveData(weatherData: DataResponse<WeatherEntity>){
        weatherLiveData.value = weatherData
    }

    fun setFav(){
        viewModelScope.launch {
            repository.addFavCity()
            getFav()
        }
    }

    fun getFav() {
        viewModelScope.launch {
            repository.getFavCityID().collect {
                updateUiFav(it)
            }
        }
    }

    private fun updateUiFav(it: FavCities?) {
        if (it != null) {
            isFav.value = it.fav
        } else {
            isFav.value = false
        }
    }


    fun fillWeatherUI(weather: WeatherEntity): List<WeatherGridViewData> {
        val list: ArrayList<WeatherGridViewData> = ArrayList()
        list.add(
            WeatherGridViewData(
                R.drawable.ic_wi_thermometer,
                "Min temp",
                weather.minTemperature
            )
        )
        list.add(
            WeatherGridViewData(
                R.drawable.ic_wi_thermometer,
                "Max temp",
                weather.maxTemperature
            )
        )
        list.add(WeatherGridViewData(R.drawable.ic_sunrise, "Sunrise", weather.sunrise))
        list.add(WeatherGridViewData(R.drawable.ic_sunset, "Sunset", weather.sunset))
        list.add(WeatherGridViewData(R.drawable.ic_wind, "Wind", weather.windSpeed))
        list.add(WeatherGridViewData(R.drawable.ic_feel_like, "Feels Like", weather.feelsLike))
        list.add(WeatherGridViewData(R.drawable.ic_pressure, "Pressure", weather.pressure))
        list.add(WeatherGridViewData(R.drawable.ic_humidity, "Humidity", weather.humidity))
        return list
    }
}

