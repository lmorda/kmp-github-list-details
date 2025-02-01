package com.lmorda.kmp.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lmorda.kmp.domain.model.GithubRepo
import com.lmorda.kmp.ui.theme.Blue80
import com.lmorda.kmp.ui.theme.Green80
import com.lmorda.kmp.ui.theme.Orange80
import com.lmorda.kmp.ui.theme.Pink80
import com.lmorda.kmp.ui.theme.Yellow80
import com.lmorda.kmp.ui.theme.mediumLargeSize
import com.lmorda.kmp.ui.theme.mediumSize
import kotlin.math.pow

@Composable
internal fun RepositoryStats(details: GithubRepo) {
    Row(
        modifier = Modifier
            .padding(vertical = mediumSize)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(size = mediumLargeSize),
            imageVector = Icons.Default.Star,
            tint = Yellow80,
            contentDescription = "star",
        )
        Text(
            modifier = Modifier.padding(horizontal = mediumSize),
            text = countPrettyString(details.stargazersCount),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Icon(
            modifier = Modifier.size(size = mediumLargeSize),
            imageVector = Icons.Default.PlayArrow,
            tint = getLanguageTintColor(language = details.language),
            contentDescription = "language",
        )
        Text(
            modifier = Modifier.padding(start = mediumSize),
            text = details.language?.takeIf { it.isNotBlank() }
                ?: "Informational",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun getLanguageTintColor(language: String?): Color =
    language?.takeIf { it.isNotBlank() }
        ?.firstOrNull()
        ?.lowercaseChar()
        ?.let { char ->
            when (char) {
                in 'a'..'e' -> Pink80
                in 'f'..'i' -> Orange80
                in 'j'..'p' -> Green80
                in 'q'..'z' -> Blue80
                else -> Pink80
            }
        } ?: Pink80

fun countPrettyString(value: Int?): String {
    if (value == null) return ""
    return when {
        value >= 1_000_000 -> "${(value / 1_000_000.0).roundTo(1)}M"
        value >= 1_000 -> "${(value / 1_000.0).roundTo(1)}k"
        else -> value.toString()
    }
}

fun Double.roundTo(decimals: Int): String {
    val factor = 10.0.pow(decimals)
    return ((kotlin.math.round(this * factor) / factor)).toString()
}
