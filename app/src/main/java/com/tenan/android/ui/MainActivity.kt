package com.tenan.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tenan.android.ui.theme.EarthyBrown100
import com.tenan.android.ui.theme.ForestGreen100
import com.tenan.android.ui.theme.TenanAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentTopDestination = topLevelDestinations.find { it.route == currentDestination?.route }
    TenanAndroidTheme {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = currentTopDestination != null,
                    enter = slideInVertically(initialOffsetY = { 0 }),
                    exit = slideOutVertically(targetOffsetY = { 0 })
                ) {
                    AppBottomNavigation(
                        currentRoute = currentDestination?.route,
                        onItemSelected = { navController.navigateSingleTopTo(it) }
                    )
                }
            }
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                startDestination = Explore,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun AppBottomNavigation(
    currentRoute: String?,
    onItemSelected: (String) -> Unit
) {
    NavigationBar {
        topLevelDestinations.forEach { item ->
            val selected = item.route == currentRoute
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title!!)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = ForestGreen100
                )
            )
        }
    }
}