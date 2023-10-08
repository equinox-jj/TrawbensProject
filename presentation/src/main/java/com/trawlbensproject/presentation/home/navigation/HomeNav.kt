package com.trawlbensproject.presentation.home.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.trawlbensproject.presentation.home.HomeScreen
import com.trawlbensproject.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeNav(
    navigateToDetail: (Int) -> Unit,
    navigateToFavorite: () -> Unit,
) {
    composable(route = "home_screen") {
        val viewModel = koinViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val lazyPagingItems = state.gameList.collectAsLazyPagingItems()

        HomeScreen(
            state = state,
            lazyPagingItems = lazyPagingItems,
            onEvent = viewModel::onEvent,
            navigateToDetail = navigateToDetail,
            navigateToFavorite = navigateToFavorite,
        )
    }
}