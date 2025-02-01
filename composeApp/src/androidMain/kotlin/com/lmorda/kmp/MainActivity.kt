package com.lmorda.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // https://issuetracker.google.com/issues/364713509
            LaunchedEffect(isSystemInDarkTheme()) {
                enableEdgeToEdge()
            }
            KmpApp()
        }
    }
}
