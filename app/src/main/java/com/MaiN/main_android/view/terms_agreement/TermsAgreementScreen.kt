package com.MaiN.main_android.view.terms_agreement

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TermsAgreementScreen(
    state: TermsAgreementState,
    actions: TermsAgreementActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "TermsAgreement")
private fun TermsAgreementScreenPreview() {
    TermsAgreementScreen(
        state = TermsAgreementState(),
        actions = TermsAgreementActions()
    )
}

