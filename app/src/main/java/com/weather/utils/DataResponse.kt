package com.weather.utils

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()  // Success
    data class Cache<out T>(val data: T) : DataResponse<T>()  // Cache local
    object Loading : DataResponse<Nothing>() // Loading data
    object Error :
        DataResponse<Nothing>() // Error Errors Details can be passed into it for custom UI as per Error
}
