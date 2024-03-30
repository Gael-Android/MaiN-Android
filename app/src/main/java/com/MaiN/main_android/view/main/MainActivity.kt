package com.MaiN.main_android.view.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.MaiN.main_android.R
import com.MaiN.main_android.view.AgreeViewActivity
import com.MaiN.main_android.view.HomeActivity
import com.MaiN.main_android.view.navhost.MaiNNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceUtil(this)
        val isLogin = prefs.getIsLogin("isLogin", false)

        if (isLogin) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        //MaiN
        styleTextView()
        //로그인 버튼
        setLoginButton()

        setContent {
            MaiNNavHost()
        }
    }

    //MaiN 글씨 커스텀
    private fun styleTextView() {
        val textView = findViewById<TextView>(R.id.textView)
        val text = "MaiN"
        val spannableString = SpannableString(text)

        val startIndex = text.indexOf("ai")
        val endIndex = startIndex + "ai".length

        val colorSpan = ForegroundColorSpan(Color.parseColor("#60B3FF"))
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
    }

    //로그인 버튼
    private fun setLoginButton() {
        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {

            val intent = Intent(this, AgreeViewActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

} 