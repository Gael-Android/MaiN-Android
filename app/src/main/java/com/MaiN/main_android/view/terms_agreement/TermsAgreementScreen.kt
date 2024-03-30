package com.MaiN.main_android.view.terms_agreement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.MaiN.main_android.R

@Composable
fun TermsAgreementScreen(
    state: TermsAgreementState,
    actions: TermsAgreementActions,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "MaiN을 이용하기 위해",
            modifier = Modifier.padding(top = 40.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "이용약관 및\n개인정보 취급방치에 동의해주세요.",
            modifier = Modifier.padding(top = 8.dp),
            color = colorResource(id = R.color.blue02),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = R.string.ServiceText),
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Black,
        )
        Button(
            modifier = Modifier
                .height(52.dp)
                .width(323.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(23.dp),
            colors = ButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White,
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "동의하기",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
@Preview(
    name = "TermsAgreement", showSystemUi = false, showBackground = true,
    device = "spec:width=1080px,height=8000px,dpi=440"
)
private fun TermsAgreementScreenPreview() {
    TermsAgreementScreen(
        state = TermsAgreementState(),
        actions = TermsAgreementActions()
    )
}

