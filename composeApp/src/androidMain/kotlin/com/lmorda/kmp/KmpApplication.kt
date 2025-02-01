package com.lmorda.kmp

import android.app.Application

class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
