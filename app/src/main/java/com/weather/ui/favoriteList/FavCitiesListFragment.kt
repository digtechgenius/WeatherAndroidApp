package com.weather.ui.favoriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.weather.R
import com.weather.ui.components.WeatherGridCardView
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


@ExperimentalFoundationApi
@Composable
fun ExampleSingleScrollable(items: List<WeatherParm>) {
    Column {
        Text(text = "London" ,  style = MaterialTheme.typography.h3,)
        Text(text = "Updated at: 22" ,  style = MaterialTheme.typography.h4,)
        Text(text = "clear sky" ,  style = MaterialTheme.typography.h3,)
        Text(text = "London" ,  style = MaterialTheme.typography.h3,)
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(items.size) { index ->
                WeatherGridCardView( items[index].icon,items[index].label,items[index].value)
            }
        }
    }
}

data class WeatherParm(val icon: Int, val label:String,val value:String)

fun fillWeatherUI(): List<WeatherParm>{
    var list: ArrayList<WeatherParm> = ArrayList()
    list.add(WeatherParm(R.drawable.ic_sunrise,"Sunrise","234"))
    list.add(WeatherParm(R.drawable.ic_sunset,"Sunset","234"))
            list.add(WeatherParm(R.drawable.ic_wind,"Wind","324"))
            list.add(WeatherParm(R.drawable.ic_feel_like,"Feels Like","324"))
            list.add(WeatherParm(R.drawable.ic_pressure,"Pressure","234"))
            list.add(WeatherParm(R.drawable.ic_humidity,"Humidity","324"))
    list.add(WeatherParm(R.drawable.ic_wind,"Wind","324"))
    list.add(WeatherParm(R.drawable.ic_feel_like,"Feels Like","324"))
    list.add(WeatherParm(R.drawable.ic_pressure,"Pressure","234"))
    list.add(WeatherParm(R.drawable.ic_humidity,"Humidity","324"))
    return list
}
/*
     setUpRecyclerView()

     favCityViewModels.favCitiesLiveDataRead.observe(viewLifecycleOwner, {
         it?.let {
             if (it.size>0){
                 progressBar.visibility = View.GONE
                 errorText.visibility = View.GONE
                 favCityAdapter.submitList(it as ArrayList<FavCities>)
             } else {
                 progressBar.visibility = View.GONE
                 recyclerView.visibility = View.GONE
                 errorText.visibility = View.VISIBLE
             }
         }
     })

 }

 private fun setUpRecyclerView() {
     favCityAdapter = FavCitiesAdapter()

     recyclerView.apply {
         adapter = favCityAdapter
         layoutManager = LinearLayoutManager(requireContext())
     }
 }

}

 */