package com.MaiN.main_android.view.saint_login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun SaintLoginRoute(
    navController: NavHostController
) {
    val coordinator: SaintLoginCoordinator =
        rememberSaintLoginCoordinator(navController = navController)

    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(SaintLoginState())

    // UI Actions
    val actions = rememberSaintLoginActions(coordinator)

    // UI Rendering
    SaintLoginScreen(uiState, actions)
}


@Composable
fun rememberSaintLoginActions(coordinator: SaintLoginCoordinator): SaintLoginActions {
    return remember(coordinator) {
        SaintLoginActions(
            onLoginSuccess = coordinator::onLoginSuccess
        )
    }
}