package com.weather.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout

// Used in all error messages
@Composable
fun ErrorMessage(message: String) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (errorPosition) = createRefs()
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(errorPosition)
                {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                },
                style = MaterialTheme.typography.h2
            )
        }

}



















