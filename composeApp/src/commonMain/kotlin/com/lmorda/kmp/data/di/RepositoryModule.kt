package com.lmorda.kmp.data.di

import com.lmorda.kmp.data.DataRepositoryImpl
import com.lmorda.kmp.data.mapper.GithubRepoMapper
import com.lmorda.kmp.domain.DataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DataRepository> {
        DataRepositoryImpl(
            apiService = get(),
            mapper = GithubRepoMapper(),
        )
    }
}
