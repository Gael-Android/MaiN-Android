package com.MaiN.main_android.view.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.MaiN.main_android.view.main.MainRoute

sealed class NavRoute(val route: String) {
    data object MainRoute : NavRoute("MainRoute")
    data object HomeRoute : NavRoute("HomeRoute")
    data object AgreeRoute : NavRoute("AgreeRoute")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

@Composable
fun MaiNNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.MainRoute.route,
    ) {
        composable(NavRoute.MainRoute.route) {
            MainRoute(navController = navController)
        }
    }
}