package com.lmorda.kmp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import com.lmorda.kmp.ui.theme.mediumLargeSize
import com.lmorda.kmp.ui.theme.mediumSize
import com.lmorda.kmp.ui.theme.standardSize
import com.lmorda.kmp.ui.theme.xLargeSize
import com.lmorda.kmp.domain.model.GithubRepo
import com.lmorda.kmp.ui.shared.EmptyScreenContent
import com.lmorda.kmp.ui.shared.RepositoryStats
import com.lmorda.kmp.ui.theme.topAppBarColors
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreenRoute(
    viewModel: DetailsViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    DetailsScreen(
        state = state,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailsContract.State,
    onBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(),
            title = { },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            },
            actions = {
                //TODO: share icon
//                val context = LocalContext.current
//                val shareText = state.githubRepo?.owner?.htmlUrl
//                IconButton(onClick = { context.shareText(text = shareText) }) {
//                    Icon(
//                        imageVector = Icons.Default.Share,
//                        contentDescription = "Share",
//                        tint = MaterialTheme.colorScheme.onBackground,
//                    )
//                }
            },
            scrollBehavior = scrollBehavior,
        )
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(vertical = mediumSize, horizontal = mediumLargeSize)
                .fillMaxSize()
        ) {
            when {
                state.exception != null -> EmptyScreenContent()
                state.githubRepo != null -> DetailsContent(details = state.githubRepo)
            }
        }
    }
}

@Composable
fun ColumnScope.DetailsContent(details: GithubRepo) {
    AsyncImage(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .size(size = xLargeSize)
            .clip(shape = CircleShape),
        model = details.owner.avatarUrl,
        contentDescription = "avatar",
    )
    Text(
        text = details.owner.login,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
    )
    Text(
        text = details.name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
    )
    Text(
        modifier = Modifier.padding(top = standardSize),
        text = details.description.orEmpty(),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
    )
    RepositoryStats(details = details)
}
