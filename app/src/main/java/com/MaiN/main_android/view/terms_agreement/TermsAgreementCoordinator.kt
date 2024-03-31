package com.MaiN.main_android.view.terms_agreement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.MaiN.main_android.view.navhost.NavRoute

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class TermsAgreementCoordinator(
    val viewModel: TermsAgreementViewModel,
    val navController: NavHostController,
) {
    val screenStateFlow = viewModel.stateFlow

    fun onAgreeClick() {
        navController.navigate(NavRoute.SaintLoginRoute.route)
    }
}

@Composable
fun rememberTermsAgreementCoordinator(
    viewModel: TermsAgreementViewModel = hiltViewModel(),
    navController: NavHostController
): TermsAgreementCoordinator {
    return remember(viewModel) {
        TermsAgreementCoordinator(
            viewModel = viewModel,
            navController = navController,
        )
    }
}