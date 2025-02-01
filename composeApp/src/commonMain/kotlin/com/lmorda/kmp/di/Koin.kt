package com.lmorda.kmp.di

import com.lmorda.kmp.data.di.dataModule
import com.lmorda.kmp.data.di.repositoryModule
import com.lmorda.kmp.ui.di.uiModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            repositoryModule,
            uiModule,
        )
    }
}
