package com.MaiN.main_android.view.home

import androidx.annotation.DrawableRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.MaiN.main_android.R
import com.MaiN.main_android.view.navhost.NavRoute

@Composable
fun HomeScreen(
    state: HomeState,
    actions: HomeActions,
) {
    val items = listOf(
        BottomNavigationInformation(
            selectedIcon = R.drawable.noti_ic_selected,
            unselectedIcon = R.drawable.noti_ic_unselected,
            label = "공지사항",
            route = NavRoute.HomeRoute.NoticeRoute
        ),
        BottomNavigationInformation(
            selectedIcon = R.drawable.reserv_ic_selected,
            unselectedIcon = R.drawable.reserv_ic_unselected,
            label = "예약",
            route = NavRoute.HomeRoute.ReservationRoute,
        ),
        BottomNavigationInformation(
            selectedIcon = R.drawable.mypage_ic_selected,
            unselectedIcon = R.drawable.mypage_ic_unselected,
            label = "마이 페이지",
            route = NavRoute.HomeRoute.MyPageRoute,
        )
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                items.forEach {
                    val isSelected = actions.isSelected(it.route)
                    BottomNavigationItem(
                        icon = {
                            if (isSelected) {
                                Icon(
                                    painter = painterResource(id = it.selectedIcon),
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = it.unselectedIcon),
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(text = it.label) },
                        selected = isSelected,
                        onClick = { actions.navigateTo(it.route) }
                    )
                }
            }
        }
    ) { it ->
        Text(text = it.toString())
    }
}

data class BottomNavigationInformation(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val label: String,
    val route: NavRoute
)

@Composable
@Preview(name = "Home")
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        actions = HomeActions()
    )
}

