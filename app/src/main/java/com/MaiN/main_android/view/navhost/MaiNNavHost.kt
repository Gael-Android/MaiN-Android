package com.MaiN.main_android.view.navhost

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.MaiN.main_android.view.main.MainRoute
import com.MaiN.main_android.view.saint_login.SaintLoginRoute
import com.MaiN.main_android.view.terms_agreement.TermsAgreementRoute

sealed class NavRoute(val route: String) {
    data object MainRoute : NavRoute("MainRoute")

    data object HomeRoute : NavRoute("HomeRoute") {
        data object NoticeRoute : NavRoute("NoticeRoute")
        data object ReservationRoute : NavRoute("ReservationRoute")
        data object MyPageRoute : NavRoute("MyPageRoute")
    }

    data object TermsAgreementRoute : NavRoute("TermsAgreementRoute")
    data object SaintLoginRoute : NavRoute("SaintLoginRoute")


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
        composable(NavRoute.TermsAgreementRoute.route) {
            TermsAgreementRoute(navController = navController)
        }
        composable(NavRoute.SaintLoginRoute.route) {
            SaintLoginRoute(navController = navController)
        }
        navigation(
            route = NavRoute.HomeRoute.route,
            startDestination = NavRoute.HomeRoute.NoticeRoute.route,
        ) {
            composable(NavRoute.HomeRoute.NoticeRoute.route) {
                // NoticeRoute(navController = navController)
                Text(text = "NoticeRoute")
            }
            composable(NavRoute.HomeRoute.ReservationRoute.route) {
                // ReservationRoute(navController = navController)
                Text(text = "ReservationRoute")
            }
            composable(NavRoute.HomeRoute.MyPageRoute.route) {
                // MyPageRoute(navController = navController)
                Text(text = "MyPageRoute")
            }
        }
    }
}