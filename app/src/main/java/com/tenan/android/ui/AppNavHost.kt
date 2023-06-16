package com.tenan.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tenan.android.ui.feature.account.AccountScreen
import com.tenan.android.ui.feature.explore.ExploreScreen
import com.tenan.android.ui.feature.search.SearchScreen
import com.tenan.android.ui.feature.search.result.SearchResultScreen
import com.tenan.android.ui.feature.tourism.DetailTourismScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: AppDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {

        composable(route = Explore.route) {
            ExploreScreen()
        }

        composable(route = Search.route) {
            SearchScreen(
                onNavigateToResult = {
                    navController.navigate(SearchResult.route)
                }
            )
        }

        composable(route = Account.route) {
            AccountScreen()
        }

        composable(route = SearchResult.route) {
            SearchResultScreen(
                onNavigateUp = navController::navigateUp,
                onTourismItemClick = {
                    navController.navigate(DetailTourism.route)
                }
            )
        }

        composable(route = DetailTourism.route) {
            DetailTourismScreen(
                onNavigateUp = navController::navigateUp
            )
        }

    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }