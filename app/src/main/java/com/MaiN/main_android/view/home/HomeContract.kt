package com.MaiN.main_android.view.home

import com.MaiN.main_android.view.navhost.NavRoute


/**
 * UI State that represents HomeScreen
 **/
data class HomeState(
    val selectedRoute: NavRoute = NavRoute.HomeRoute.NoticeRoute
)

/**
 * Home Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class HomeActions(
    val isSelected: (NavRoute) -> Boolean = { false },
    val navigateTo: (NavRoute) -> Unit = { },

    )