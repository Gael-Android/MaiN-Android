package com.MaiN.main_android.view.user_agreement


/**
 * UI State that represents UserAgreementScreen
 **/
class UserAgreementState

/**
 * UserAgreement Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class UserAgreementActions(
    val onClick: () -> Unit = {}
)