package com.lmorda.kmp.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmp_github_list_details.composeapp.generated.resources.Res
import kmp_github_list_details.composeapp.generated.resources.no_data_available
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(Res.string.no_data_available))
    }
}
