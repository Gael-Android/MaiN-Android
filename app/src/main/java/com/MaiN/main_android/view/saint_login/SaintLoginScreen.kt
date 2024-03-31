package com.MaiN.main_android.view.saint_login

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SaintLoginScreen(
    state: SaintLoginState,
    actions: SaintLoginActions,
) {
    val url =
        "https://smartid.ssu.ac.kr/Symtra_sso/smln.asp?apiReturnUrl=https%3A%2F%2Fsaint.ssu.ac.kr%2FwebSSO%2Fsso.jsp"
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = CustomWebViewClient(onLoginSuccess = actions.onLoginSuccess)

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = false
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )


}

class CustomWebViewClient(
    private val onLoginSuccess: (schoolNumber: String) -> Unit
) : WebViewClient() {

    private val getDepartmentJs =
        "document.querySelector('body > div > div.main_wrap > div.main_left > div.main_box09 > div.main_box09_con_w > ul > li:nth-child(2) > dl > dd > a > strong').innerText"

    private val getSchoolNumberJs =
        "document.querySelector('body > div > div.main_wrap > div.main_left > div.main_box09 > div.main_box09_con_w > ul > li:nth-child(1) > dl > dd > a > strong').innerText"


    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.d("WebView", "onPageStarted: $url")
        if (url == "https://saint.ssu.ac.kr/irj/portal") {
            view?.loadUrl("https://saint.ssu.ac.kr/webSSUMain/main_student.jsp")
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d("WebView", "onPageFinished: $url")
        if (url == "https://saint.ssu.ac.kr/webSSUMain/main_student.jsp") {
            view?.evaluateJavascript(getDepartmentJs) { department ->
                view.evaluateJavascript(getSchoolNumberJs) { schoolNumber ->
                    if (department == "\"AI융합학부\"") {
                        Log.d("WebView", "department: $department")
                        Log.d("WebView", "schoolNumber: $schoolNumber")
                        onLoginSuccess(schoolNumber.replace("\"", ""))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "SaintLogin")
private fun SaintLoginScreenPreview() {
    SaintLoginScreen(
        state = SaintLoginState(),
        actions = SaintLoginActions()
    )
}

