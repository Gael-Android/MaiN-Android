package com.MaiN.main_android.view.user_agreement

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserAgreementScreen(
    state: UserAgreementState,
    actions: UserAgreementActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "UserAgreement")
private fun UserAgreementScreenPreview() {
    UserAgreementScreen(
        state = UserAgreementState(),
        actions = UserAgreementActions()
    )
}

