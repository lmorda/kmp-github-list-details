package com.lmorda.kmp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = BrandLight,
    background = White,
    onPrimary = Black,
    onBackground = Grey220,
)

private val DarkColorScheme = darkColorScheme(
    primary = BrandDark,
    background = BrandDarkest,
    onPrimary = White,
    onBackground = Grey20,
)

@Composable
fun KmpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBarColors() = TopAppBarColors(
    containerColor = MaterialTheme.colorScheme.background,
    titleContentColor = MaterialTheme.colorScheme.background,
    actionIconContentColor = MaterialTheme.colorScheme.background,
    navigationIconContentColor = MaterialTheme.colorScheme.background,
    scrolledContainerColor = MaterialTheme.colorScheme.background,
)

// TODO: Use device specific sizes like dimensionResource in AndroidX Compose UI
val smallSize: Dp = 4.dp
val mediumSize: Dp = 8.dp
val standardSize: Dp = 16.dp
val mediumLargeSize: Dp = 24.dp
val largeSize: Dp = 32.dp
val xLargeSize: Dp = 128.dp
