

package com.example.android.trackr

import android.app.Application

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_MaterialComponents)
    }
}