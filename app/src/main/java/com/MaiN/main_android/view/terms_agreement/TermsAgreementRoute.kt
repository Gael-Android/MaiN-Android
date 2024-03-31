package com.MaiN.main_android.view.terms_agreement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun TermsAgreementRoute(
    navController: NavHostController,
) {
    val coordinator: TermsAgreementCoordinator =
        rememberTermsAgreementCoordinator(navController = navController)

    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(TermsAgreementState())

    // UI Actions
    val actions = rememberTermsAgreementActions(coordinator)

    // UI Rendering
    TermsAgreementScreen(uiState, actions)
}


@Composable
fun rememberTermsAgreementActions(coordinator: TermsAgreementCoordinator): TermsAgreementActions {
    return remember(coordinator) {
        TermsAgreementActions(
            onAgreeClick = coordinator::onAgreeClick
        )
    }
}