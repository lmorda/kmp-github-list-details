package com.lmorda.kmp.data

import com.lmorda.kmp.data.mapper.GithubRepoMapper
import com.lmorda.kmp.domain.DataRepository

const val PER_PAGE = 30
const val QUERY = "android"
const val ORDER = "desc"
const val SORT = "stars"

class DataRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: GithubRepoMapper,
) : DataRepository {

    override suspend fun getRepos(page: Int) =
        mapper.map(
            githubReposDto = apiService.searchRepositories(
                page = page,
                perPage = PER_PAGE,
                query = QUERY,
                order = ORDER,
                sort = SORT,
            )
        )

    override suspend fun getRepo(id: Long) =
        mapper.map(
            githubRepoDto = apiService.getRepo(id = id)
        )
}
