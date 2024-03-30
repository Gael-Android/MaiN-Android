package com.MaiN.main_android.view.terms_agreement


/**
 * UI State that represents TermsAgreementScreen
 **/
class TermsAgreementState

/**
 * TermsAgreement Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class TermsAgreementActions(
    val onClick: () -> Unit = {}
)