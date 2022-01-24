package com.weather.ui.favoriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.weather.R
import com.weather.ui.components.WeatherList
import com.weather.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] to show list of fav cities
 */
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class FavCitiesListFragment : Fragment() {

    private val favCityViewModels by viewModels<FavCitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            favCityViewModels.favCitiesLiveDataRead.observe(viewLifecycleOwner, { uiState ->
                run {
                    setContent {
                        AppTheme(
                            content =
                            {
                                Scaffold {
                                    WeatherList(
                                        uiState = uiState,
                                        onNavigateToWeatherDetailScreen = {
                                            val bundle = Bundle()
                                            bundle.putString("city", it)
                                            findNavController().navigate(
                                                R.id.WeatherFragment,
                                                bundle
                                            )
                                        }
                                    )
                                }
                            }
                        )
                    }
                }
            }
            )
        }
    }
}