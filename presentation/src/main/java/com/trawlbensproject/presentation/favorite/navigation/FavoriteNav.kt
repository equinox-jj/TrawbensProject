package com.trawlbensproject.presentation.favorite.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.trawlbensproject.presentation.favorite.FavoriteScreen
import com.trawlbensproject.presentation.favorite.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.favoriteNav(
    popBackStack: () -> Unit,
) {
    composable(route = "favorite_screen") {
        val viewModel = koinViewModel<FavoriteViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        FavoriteScreen(
            state = state,
            onEvent = viewModel::onEvent,
            popBackStack = popBackStack,
        )
    }
}