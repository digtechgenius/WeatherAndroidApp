package com.weather.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable


@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = WhitePlain,
    onSecondary = BlackPlain,
    error = RedErrorDark,
    background = Grey,
    onBackground = BlackPlain,
    surface = WhitePlain,
    onSurface = WhitePlain,
)

@SuppressLint("ConflictingOnColor")
private val DarkThemeColors = darkColors(
    primary = Blue700,
    primaryVariant = WhitePlain,
    onPrimary = WhitePlain,
    secondary = BlackPlain,
    onSecondary = WhitePlain,
    error = RedErrorLight,
    background = BlackPlain,
    onBackground = WhitePlain,
    surface = BlackPlain,
    onSurface = WhitePlain,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ){
        content()
    }
}





























