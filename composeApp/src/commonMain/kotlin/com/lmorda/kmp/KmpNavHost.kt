package com.lmorda.kmp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lmorda.kmp.ui.details.DetailsScreenRoute
import com.lmorda.kmp.ui.list.ExploreScreenRoute

const val routeExplore = "explore"
const val routeDetailsBase = "details"
const val argDetailsId = "id"
const val routeDetailsFull = "$routeDetailsBase/{$argDetailsId}"

@Composable
internal fun KmpNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = routeExplore,
    ) {
        composable(route = routeExplore) {
            ExploreScreenRoute(
                onNavigateToDetails = { id ->
                    navController.navigate("$routeDetailsBase/$id")
                },
            )
        }
        composable(
            route = routeDetailsFull,
            arguments = listOf(
                navArgument(name = argDetailsId) { type = NavType.LongType },
            ),
        ) {
            DetailsScreenRoute(
                onBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
