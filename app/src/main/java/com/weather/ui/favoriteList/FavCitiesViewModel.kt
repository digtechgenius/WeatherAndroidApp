package com.weather.ui.favoriteList

import androidx.lifecycle.*
import com.weather.data.dto.FavCities
import com.weather.data.repository.WeatherRepositoryImpl
import com.weather.utils.DataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavCitiesViewModel @Inject constructor(
    repository: WeatherRepositoryImpl
) : ViewModel() {

    private var favCitiesLiveData: LiveData<DataResponse<List<FavCities>>> = MutableLiveData()

    val favCitiesLiveDataRead: LiveData<DataResponse<List<FavCities>>>
        get() = favCitiesLiveData

    init {
        viewModelScope.launch {
            favCitiesLiveData = repository.getFavCities().asLiveData(viewModelScope.coroutineContext)
        }
    }

}