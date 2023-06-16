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
                    navController.navigate(route = SearchResult.buildRouteWithArgs(it))
                }
            )
        }

        composable(route = Account.route) {
            AccountScreen()
        }

        composable(
            route = SearchResult.routeWithArgs,
            arguments = SearchResult.arguments
        ) { navBackStack ->
            SearchResultScreen(
                query = navBackStack.arguments?.getString(SearchResult.query) ?: "",
                onNavigateUp = navController::navigateUp,
                onTourismItemClick = {
                    navController.navigate(DetailTourism.buildRouteWithArgs(it))
                }
            )
        }

        composable(
            route = DetailTourism.routeWithArgs,
            arguments = DetailTourism.arguments
        ) { navBackStack ->
            DetailTourismScreen(
                tourismId = navBackStack.arguments?.getInt(DetailTourism.tourismId) ?: 0,
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