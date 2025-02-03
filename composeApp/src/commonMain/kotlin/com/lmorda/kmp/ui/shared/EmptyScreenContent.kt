package com.lmorda.kmp.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.lmorda.kmp.ui.theme.standardSize
import kmp_github_list_details.composeapp.generated.resources.Res
import kmp_github_list_details.composeapp.generated.resources.error_dark
import kmp_github_list_details.composeapp.generated.resources.error_light
import kmp_github_list_details.composeapp.generated.resources.list_error
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyScreenContent() {
    Column(modifier = Modifier.padding(standardSize)) {
        Text(
            text = stringResource(Res.string.list_error),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Image(
            modifier = Modifier.align(alignment = CenterHorizontally).padding(top = standardSize),
            painter = if (isSystemInDarkTheme()) painterResource(Res.drawable.error_dark) else
                painterResource(Res.drawable.error_light),
            contentDescription = null,
        )
    }
}
