package com.MaiN.main_android.view.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun MainRoute(
    navController: NavHostController,
) {
    val coordinator: MainCoordinator = rememberMainCoordinator(navController = navController)

    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(MainState())

    // UI Actions
    val actions = rememberMainActions(coordinator)

    // UI Rendering
    MainScreen(uiState, actions)
}


@Composable
fun rememberMainActions(coordinator: MainCoordinator): MainActions {
    return remember(coordinator) {
        MainActions(
            onLoginClick = coordinator::onLoginClick
        )
    }
}