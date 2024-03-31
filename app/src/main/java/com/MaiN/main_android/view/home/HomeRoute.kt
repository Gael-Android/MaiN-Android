package com.MaiN.main_android.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun HomeRoute(
    navController: NavHostController
) {
    val coordinator: HomeCoordinator = rememberHomeCoordinator(navController = navController)

    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(HomeState())

    // UI Actions
    val actions = rememberHomeActions(coordinator)

    // UI Rendering
    HomeScreen(uiState, actions)
}


@Composable
fun rememberHomeActions(coordinator: HomeCoordinator): HomeActions {
    return remember(coordinator) {
        HomeActions(
            isSelected = coordinator::isSelected,
            navigateTo = coordinator::navigateTo
        )
    }
}