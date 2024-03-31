package com.MaiN.main_android.view.saint_login


/**
 * UI State that represents SaintLoginScreen
 **/
data class SaintLoginState(
    val department: String = "",
    val schoolNumber: String = ""
)

/**
 * SaintLogin Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SaintLoginActions(
    val onLoginSuccess: (schoolNumber: String) -> Unit = {}
)