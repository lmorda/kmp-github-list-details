package com.lmorda.kmp.domain.model

data class GithubRepo(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String?,
    val stargazersCount: Int?,
    val language: String?,
)
