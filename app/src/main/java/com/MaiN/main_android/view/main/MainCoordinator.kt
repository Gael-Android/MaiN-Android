package com.MaiN.main_android.view.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.MaiN.main_android.view.navhost.NavRoute

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MainCoordinator(
    val viewModel: MainViewModel,
    val navController: NavHostController,
) {
    val screenStateFlow = viewModel.stateFlow

    fun onLoginClick() {
        navController.navigate(NavRoute.AgreeRoute.route)
    }
}

@Composable
fun rememberMainCoordinator(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController
): MainCoordinator {
    return remember(viewModel) {
        MainCoordinator(
            viewModel = viewModel,
            navController = navController,
        )
    }
}