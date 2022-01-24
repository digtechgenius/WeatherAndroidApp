package com.weather.ui.weatherDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.birjuvachhani.locus.Locus
import com.weather.R
import com.weather.ui.components.CircularProgressBar
import com.weather.ui.components.ErrorMessage
import com.weather.ui.components.SearchAppBar
import com.weather.ui.components.WeatherDetailView
import com.weather.ui.theme.AppTheme
import com.weather.utils.AppConstants
import com.weather.utils.AppConstants.LOCATION_ERROR_MESSAGE
import com.weather.utils.DataResponse
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val viewModels by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments == null) {
            fetchLocation()
        }
        arguments?.getString("city")?.let { city ->
            viewModels.searchWeather(city)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            viewModels.weatherLiveDataRead.observe(viewLifecycleOwner, { uiState ->
                run {
                    setContent {
                        val isFav = viewModels.isFav.value
                        val query = remember { mutableStateOf("") }
                        AppTheme(
                            content =
                            {
                                Scaffold(
                                    topBar = {
                                        SearchAppBar(
                                            query = query.value,
                                            onQueryChanged = {
                                                query.value = it
                                            },
                                            onExecuteSearch = {
                                                viewModels.searchWeather(query.value)
                                            },
                                            showFavList = {
                                                findNavController().navigate(
                                                    R.id.FavFragment
                                                )
                                            }
                                        )
                                    },
                                ) {

                                    when (uiState) {
                                        is DataResponse.Loading -> {
                                            CircularProgressBar(true)
                                        }
                                        is DataResponse.Success -> {
                                            WeatherDetailView(
                                                { viewModels.setFav() }, isFav,
                                                uiState.data,
                                                viewModels.fillWeatherUI(uiState.data),
                                                {
                                                    fetchLocation()
                                                })
                                            viewModels.getFav()
                                        }
                                        is DataResponse.Cache -> {

                                            WeatherDetailView(
                                                { viewModels.setFav() }, isFav,
                                                uiState.data,
                                                viewModels.fillWeatherUI(uiState.data),
                                                {
                                                    fetchLocation()
                                                })
                                            viewModels.getFav()
                                        }
                                        is DataResponse.Error -> {
                                            ErrorMessage(uiState.message)
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
            )
        }
    }

    private fun fetchLocation() {
        context?.let {
            Locus.getCurrentLocation(it) { result ->
                result.location?.let {
                    AppConstants.location = it
                    viewModels.getCurrentWeather()
                }
                result.error?.let {
                    Toast.makeText(requireContext(), LOCATION_ERROR_MESSAGE, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}






