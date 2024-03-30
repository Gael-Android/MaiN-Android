package com.MaiN.main_android.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MaiN.main_android.R

@Composable
fun MainScreen(
    state: MainState,
    actions: MainActions,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.login_backgroud),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MaiN",
                fontFamily = FontFamily(
                    Font(R.font.inter_bold, FontWeight.Bold)
                ),
                fontSize = 80.sp,
                color = Color.Black,
            )
            Text(
                text = "SSU AI융합학부\n공지사항 및 예약서비스",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .width(323.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(23.dp),
                colors = ButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Black,
                    disabledContentColor = Color.White,
                ),
                onClick = { actions.onLoginClick() }
            ) {
                Text(
                    text = "USAINT 로그인",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
@Preview(name = "Main", showBackground = true)
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(),
        actions = MainActions()
    )
}

