package com.MaiN.main_android.view.user_agreement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class UserAgreementCoordinator(
    val viewModel: UserAgreementViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberUserAgreementCoordinator(
    viewModel: UserAgreementViewModel = hiltViewModel()
): UserAgreementCoordinator {
    return remember(viewModel) {
        UserAgreementCoordinator(
            viewModel = viewModel
        )
    }
}