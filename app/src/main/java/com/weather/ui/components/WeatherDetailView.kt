package com.weather.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.data.dto.WeatherEntity


// Weather Screen View (Compound Component)
@ExperimentalFoundationApi
@Composable
fun WeatherDetailView(setFav: () -> Unit, isFav: Boolean, weatherEntity: WeatherEntity, items: List<WeatherGridViewData>, fetchLocation: () -> Unit) {
    val iconFav = if (isFav) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(modifier = Modifier.size(35.dp),
                onClick = {
                    setFav()
                }) {

                Icon(
                    imageVector = iconFav,
                    "contentDescription",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(35.dp)
                )
            }
            Text(text = weatherEntity.name, style = MaterialTheme.typography.h2)
            IconButton(modifier = Modifier.size(35.dp),
                onClick = {
                    fetchLocation()
                }) {
                Icon(
                    Icons.Filled.LocationOn,
                    "contentDescription",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Text(text =  weatherEntity.updatedDate, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = weatherEntity.description, style = MaterialTheme.typography.h3)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = weatherEntity.temperature, style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(items.size) { index ->
                WeatherGridCardView(items[index].icon, items[index].label, items[index].value)
            }
        }
    }
}