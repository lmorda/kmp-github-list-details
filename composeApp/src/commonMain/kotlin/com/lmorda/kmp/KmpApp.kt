package com.lmorda.kmp

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lmorda.kmp.ui.theme.KmpTheme

@Composable
fun KmpApp() {
    KmpTheme {
        Surface {
            val navController: NavHostController = rememberNavController()
            KmpNavHost(navController)
        }
    }
}
