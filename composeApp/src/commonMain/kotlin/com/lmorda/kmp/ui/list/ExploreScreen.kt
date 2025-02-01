package com.lmorda.kmp.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lmorda.kmp.ui.theme.PaginationEffect
import com.lmorda.kmp.ui.theme.largeSize
import com.lmorda.kmp.ui.theme.mediumSize
import com.lmorda.kmp.ui.theme.smallSize
import com.lmorda.kmp.ui.theme.standardSize
import com.lmorda.kmp.domain.model.GithubRepo
import com.lmorda.kmp.domain.model.mockDomainData
import com.lmorda.kmp.ui.shared.EmptyScreenContent
import com.lmorda.kmp.ui.shared.RepositoryStats
import com.lmorda.kmp.ui.theme.KmpTheme
import com.lmorda.kmp.ui.theme.topAppBarColors
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExploreScreenRoute(
    viewModel: ExploreViewModel = koinViewModel(),
    onNavigateToDetails: (Long) -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    ExploreScreen(
        state = state,
        onNextPage = viewModel::getNextPage,
        onRefresh = viewModel::onRefresh,
        onNavigateToDetails = onNavigateToDetails,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExploreScreen(
    state: ExploreContract.State,
    onNextPage: () -> Unit,
    onRefresh: () -> Unit,
    onNavigateToDetails: (Long) -> Unit,
) {
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(),
                title = {
                    Text(
                        text = "Repositories", // TODO: Use string resource
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ExploreContent(
                state = state,
                listState = listState,
                onNextPage = onNextPage,
                onNavigateToDetails = onNavigateToDetails,
            )
        }
    }
}

@Composable
private fun ExploreContent(
    state: ExploreContract.State,
    listState: LazyListState,
    onNextPage: () -> Unit,
    onNavigateToDetails: (Long) -> Unit,
) {
    when {
        state.exception != null -> EmptyScreenContent()
        // state.isFirstLoad -> ExploreProgressIndicator()  //TODO: progress indicator
        else -> ExploreList(
            listState = listState,
            state = state,
            onNavigateToDetails = onNavigateToDetails,
        )
    }
    if (!state.isLoading()) {
        PaginationEffect(
            listState = listState,
            buffer = 5, // Load more when 5 items away from the end
            onLoadMore = onNextPage,
        )
    }
}

@Composable
private fun ExploreList(
    listState: LazyListState,
    state: ExploreContract.State,
    onNavigateToDetails: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
    ) {
        items(state.githubRepos) { details ->
            ExploreItem(details = details, onNavigateToDetails = onNavigateToDetails)
        }
        if (state.isFetchingNextPage) {
            item { ExploreNextPageIndicator() }
        }
    }
}

@Composable
private fun ExploreNextPageIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumSize),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(width = largeSize),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun ExploreItem(details: GithubRepo, onNavigateToDetails: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .padding(all = standardSize)
            .clickable {
                onNavigateToDetails(details.id)
            }
    ) {
        ExploreItemTitle(details = details)

        Text(
            modifier = Modifier.padding(top = smallSize),
            text = details.description.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
        )

        RepositoryStats(details)
    }
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp,
    )
}

@Composable
private fun ExploreItemTitle(details: GithubRepo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(size = largeSize)
                    .clip(shape = CircleShape),
                model = details.owner.avatarUrl,
                contentDescription = "avatar",
            )
            Text(
                modifier = Modifier.padding(start = mediumSize),
                text = details.owner.login,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
        }
        Text(
            modifier = Modifier.padding(top = smallSize),
            text = details.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@Preview
private fun ExploreScreenPreview() {
    KmpTheme {
        ExploreScreen(
            state = ExploreContract.State(
                githubRepos = mockDomainData,
            ),
            onNextPage = {},
            onRefresh = {},
            onNavigateToDetails = {},
        )
    }
}
