package com.weather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.weather.ui.theme.AppShapes

data class WeatherGridViewData(val icon: Int, val label: String, val value: String)

// Weather Screen Grid Card View
@Composable
fun WeatherGridCardView(icon: Int, label: String, value: String  ) {

    Card(
        shape = AppShapes.large,
        modifier = Modifier
            .padding(
                bottom = 20.dp,
                top = 20.dp,
                start = 20.dp,
                end = 20.dp
            )
            .background(color = MaterialTheme.colors.secondary),
        elevation = 8.dp,
    ) {
        Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(
                bottom = 20.dp,
                top = 20.dp,
                start = 20.dp,
                end =20.dp
            )) {
            Icon(painterResource(icon),label, modifier = Modifier.size(40.dp), tint = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = label ,  style = MaterialTheme.typography.h3)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = value,  style = MaterialTheme.typography.h3)
        }
    }
}


















