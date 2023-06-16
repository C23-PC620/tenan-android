package com.tenan.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.tenan.android.R

interface AppDestination {
    val icon: ImageVector?
    val title: Int?
    val route: String
}

object Explore : AppDestination {
    override val icon: ImageVector = Icons.Rounded.Explore
    override val title: Int = R.string.str_explore
    override val route = "explore"
}

object Search : AppDestination {
    override val icon: ImageVector = Icons.Rounded.Search
    override val title: Int = R.string.str_search
    override val route = "search"
}

object Account : AppDestination {
    override val icon: ImageVector = Icons.Rounded.AccountBox
    override val title: Int = R.string.str_account
    override val route = "account"
}

object SearchResult : AppDestination {
    override val icon: Nothing? = null
    override val title: Nothing? = null
    override val route = "search/result"
    const val query = "query"
    val routeWithArgs = "$route/{$query}"
    val arguments = listOf(
        navArgument(query) { type = NavType.StringType },
    )

    fun buildRouteWithArgs(
        query: String
    ) = "$route/$query"
}

object DetailTourism : AppDestination {
    override val icon: Nothing? = null
    override val title: Nothing? = null
    override val route = "tourism/detail"
}

val topLevelDestinations = listOf(Explore, Search, Account)