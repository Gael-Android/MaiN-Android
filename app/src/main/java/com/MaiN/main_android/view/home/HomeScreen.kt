package com.MaiN.main_android.view.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    state: HomeState,
    actions: HomeActions,
) {

}

@Composable
@Preview(name = "Home")
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        actions = HomeActions()
    )
}

