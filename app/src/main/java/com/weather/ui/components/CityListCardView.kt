package com.weather.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Used in Fav City item
@Composable
fun CityListCardView(name: String, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                top = 10.dp,
                start = 8.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    bottom = 10.dp,
                    top = 10.dp,
                    start = 8.dp
                ),
            text = name,
            style = MaterialTheme.typography.h2
        )
    }
}

















