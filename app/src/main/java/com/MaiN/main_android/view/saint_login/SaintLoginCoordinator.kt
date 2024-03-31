package com.MaiN.main_android.view.saint_login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.MaiN.main_android.shared_preference.SharedPreferencesManager
import com.MaiN.main_android.view.navhost.NavRoute

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SaintLoginCoordinator(
    val viewModel: SaintLoginViewModel,
    val navController: NavHostController
) {
    val screenStateFlow = viewModel.stateFlow

    fun onLoginSuccess(schoolNumber: String) {
        // Handle login success
        SharedPreferencesManager.run {
            setSchoolNumber("schoolNumber", schoolNumber)
            setIsLogin("isLogin", true)
        }
        viewModel.addUser(schoolNumber)
        navigateToHome()
    }

    private fun navigateToHome() {
        navController.navigate(NavRoute.HomeRoute.route)
    }
}

@Composable
fun rememberSaintLoginCoordinator(
    viewModel: SaintLoginViewModel = hiltViewModel(),
    navController: NavHostController
): SaintLoginCoordinator {
    return remember(viewModel) {
        SaintLoginCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}