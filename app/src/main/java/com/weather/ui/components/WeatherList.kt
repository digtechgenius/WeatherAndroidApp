package com.weather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.data.dto.FavCities
import com.weather.utils.DataResponse

// Fav City List Screen ( Compound Component)
@Composable
fun WeatherList(
    uiState: DataResponse<List<FavCities>>,
    onNavigateToWeatherDetailScreen: (String) -> Unit,
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
        .fillMaxHeight()
    ) {

        when (uiState) {
            is DataResponse.Loading -> {
                CircularProgressBar(true)
            }
            is DataResponse.Success -> {
                if(uiState.data.isNullOrEmpty()){
                    ErrorMessage(message = "No favourite Cities yet! Save them using favourite icon on Weather Detail Screen")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(4.dp)
                    ) {

                        items(uiState.data) { favCity ->
                            CityListCardView(favCity.cityName, onClick = {
                                onNavigateToWeatherDetailScreen(favCity.cityName)
                            })

                        }
                    }
                }
            }
            is DataResponse.Cache -> {
                if(uiState.data.isNullOrEmpty()){
                    ErrorMessage(message = "No Favorite Cities yet! Save them using Favorite icon on Weather Detail Screen")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(4.dp)
                    ) {

                        items(uiState.data) { favCity ->
                            CityListCardView(favCity.cityName, onClick = {
                                onNavigateToWeatherDetailScreen(favCity.cityName)
                            })

                        }
                    }
                }
            }
            is DataResponse.Error -> {
                ErrorMessage("OOPS! Some Error occurred ")
            }

        }
    }
}









