package com.trawbensproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.trawlbensproject.presentation.detail.navigation.detailNav
import com.trawlbensproject.presentation.favorite.navigation.favoriteNav
import com.trawlbensproject.presentation.home.navigation.homeNav

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeNav(
            navigateToDetail = {
                navController.navigate("detail_screen/$it")
            },
            navigateToFavorite = {
                navController.navigate("favorite_screen")
            }
        )
        detailNav()
        favoriteNav(
            popBackStack = {
                navController.popBackStack()
            }
        )
    }
}