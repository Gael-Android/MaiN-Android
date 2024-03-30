package com.MaiN.main_android.view.navhost

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MaiNNavHost(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "MaiNNavHost")
    }
}

@Preview(name = "MaiNNavHost")
@Composable
private fun PreviewMaiNNavHost() {
    MaiNNavHost()
}