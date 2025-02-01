package com.lmorda.kmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lmorda.kmp.data.di.dataModule
import com.lmorda.kmp.data.di.repositoryModule
import com.lmorda.kmp.ui.di.uiModule
import org.koin.core.context.startKoin

@Composable
fun KmpApp() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val navController: NavHostController = rememberNavController()
            KmpNavHost(navController)
        }
    }
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            repositoryModule,
            uiModule,
        )
    }
}
