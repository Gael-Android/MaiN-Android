package com.MaiN.main_android.view.main


/**
 * UI State that represents MainScreen
 **/
class MainState

/**
 * Main Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MainActions(
    val onCreate: () -> Unit = {},
    val onLoginClick: () -> Unit = {}
)