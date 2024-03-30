package com.MaiN.main_android.view.user_agreement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun UserAgreementRoute(
    coordinator: UserAgreementCoordinator = rememberUserAgreementCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(UserAgreementState())

    // UI Actions
    val actions = rememberUserAgreementActions(coordinator)

    // UI Rendering
    UserAgreementScreen(uiState, actions)
}


@Composable
fun rememberUserAgreementActions(coordinator: UserAgreementCoordinator): UserAgreementActions {
    return remember(coordinator) {
        UserAgreementActions(
            onClick = coordinator::doStuff
        )
    }
}