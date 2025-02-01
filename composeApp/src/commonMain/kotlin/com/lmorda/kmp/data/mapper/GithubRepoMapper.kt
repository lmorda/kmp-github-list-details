package com.lmorda.kmp.data.mapper

import com.lmorda.kmp.data.model.GithubRepoDto
import com.lmorda.kmp.data.model.GithubReposDto
import com.lmorda.kmp.domain.model.GithubRepo
import com.lmorda.kmp.domain.model.Owner

class GithubRepoMapper {

    fun map(githubReposDto: GithubReposDto) =
        githubReposDto.items.map {
            map(githubRepoDto = it)
        }

    fun map(githubRepoDto: GithubRepoDto) =
        with(githubRepoDto) {
            GithubRepo(
                id = id,
                name = name,
                owner = Owner(
                    login = owner.login,
                    avatarUrl = owner.avatarUrl,
                    htmlUrl = owner.htmlUrl,
                ),
                description = description,
                stargazersCount = stargazersCount,
                language = language,
            )
        }
}
