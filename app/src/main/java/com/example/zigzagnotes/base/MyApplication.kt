package com.example.zigzagnotes.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.zigzagnotes.util.LocaleHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.setLocale(this, LocaleHelper.getPersistedLanguage(this))
    }
}