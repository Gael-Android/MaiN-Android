package com.MaiN.main_android.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.MaiN.main_android.view.navhost.NavRoute

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class HomeCoordinator(
    val viewModel: HomeViewModel,
    val navController: NavHostController
) {
    val screenStateFlow = viewModel.stateFlow

    fun isSelected(nowRoute: NavRoute): Boolean {
        val navBackStackEntry = navController.currentBackStackEntry
        val currentRoute = navBackStackEntry?.destination

        return currentRoute?.hierarchy?.any { it.route == nowRoute.route } == true
    }

    fun navigateTo(route: NavRoute) {
        navController.navigate(route.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberHomeCoordinator(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
): HomeCoordinator {
    return remember(viewModel) {
        HomeCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}