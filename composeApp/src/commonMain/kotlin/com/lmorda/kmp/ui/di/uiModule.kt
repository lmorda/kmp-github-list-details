package com.lmorda.kmp.ui.di

import com.lmorda.kmp.ui.details.DetailsViewModel
import com.lmorda.kmp.ui.list.ExploreViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ExploreViewModel(get()) }
    viewModel { DetailsViewModel(get(), get()) }
}
