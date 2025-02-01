package com.lmorda.kmp.data.model

import com.lmorda.data.model.OwnerDto

val mockApiData = GithubReposDto(
    items = listOf(
        GithubRepoDto(
            id = 0,
            name = "my-application",
            owner = OwnerDto("google", "", ""),
            description = "description for google my application",
            stargazersCount = 345123,
            language = "Kotlin",
        ),
        GithubRepoDto(
            id = 1,
            name = "my-application-2",
            owner = OwnerDto("google", "", ""),
            description = "description for google my application 2",
            stargazersCount = 1234,
            language = "Java",
        ),
    ),
)
