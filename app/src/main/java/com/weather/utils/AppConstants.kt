package com.weather.utils

import android.location.Location
import com.weather.BuildConfig

// Application Wide Constants are placed here
object AppConstants {

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val DB_NAME = "tb_weather"
    const val apiKey =
        BuildConfig.API_TOKEN // Highly secure Key should be inside Native code. Built using NDK
    lateinit var location: Location
    var sessionLocalWeatherValid: Boolean = false
    const val STANDARD_ERROR_MESSAGE: String =
        "OOPS! Error \n Please check your search name and internet " // This can be replaced from CMS
    const val LOCATION_ERROR_MESSAGE: String = "Error getting location. Please check permissions"
    const val FAV_EMPTY_MESSAGE: String =
        "No favourite Cities yet! Save them using favourite icon on Weather Detail Screen"
    const val FIREBASE_CONFIG_UPDATE: Long = 86400
    const val KEY_UPDATE_REQUIRED = "force_update_required"
    const val KEY_CURRENT_VERSION = "force_update_current_version"
    const val KEY_UPDATE_URL = "force_update_store_url"
}

object RemoteConfigs{
    var updateURL = "https://play.google.com/store/apps/"
    var isUpdateReq = false
    var updateVersion = "1.0.0"
}
