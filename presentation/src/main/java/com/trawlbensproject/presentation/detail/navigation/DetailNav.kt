package com.trawlbensproject.presentation.detail.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.trawlbensproject.presentation.detail.DetailScreen
import com.trawlbensproject.presentation.detail.DetailViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.detailNav() {
    composable(
        route = "detail_screen/{gameId}",
        arguments = listOf(
            navArgument(name = "gameId") {
                type = NavType.IntType
            }
        )
    ) {
        val viewModel = koinViewModel<DetailViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        DetailScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}