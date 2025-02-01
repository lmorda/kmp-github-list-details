package com.lmorda.kmp.domain

import com.lmorda.kmp.domain.model.GithubRepo

interface DataRepository {

    suspend fun getRepos(page: Int): List<GithubRepo>

    suspend fun getRepo(id: Long): GithubRepo
}
