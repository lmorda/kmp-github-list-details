package com.lmorda.kmp

import android.app.Application
import com.lmorda.kmp.di.initKoin

class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
