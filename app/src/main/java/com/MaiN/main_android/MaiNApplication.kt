package com.MaiN.main_android

import android.app.Application
import com.MaiN.main_android.shared_preference.SharedPreferencesManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MaiNApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.init(applicationContext)
    }
}