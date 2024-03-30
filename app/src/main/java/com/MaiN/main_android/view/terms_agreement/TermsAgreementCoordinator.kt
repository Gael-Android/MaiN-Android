package com.MaiN.main_android.view.terms_agreement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class TermsAgreementCoordinator(
    val viewModel: TermsAgreementViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}

@Composable
fun rememberTermsAgreementCoordinator(
    viewModel: TermsAgreementViewModel = hiltViewModel()
): TermsAgreementCoordinator {
    return remember(viewModel) {
        TermsAgreementCoordinator(
            viewModel = viewModel
        )
    }
}