package com.lmorda.kmp.data

import com.lmorda.kmp.data.model.GithubRepoDto
import com.lmorda.kmp.data.model.GithubReposDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

const val BASE_URL = "https://api.github.com"

class ApiService(
    private val client: HttpClient,
) {
    suspend fun searchRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    ): GithubReposDto {
        return client.get("$BASE_URL/search/repositories") {
            url {
                parameters.append("q", query)
                parameters.append("sort", sort)
                parameters.append("order", order)
                parameters.append("page", page.toString())
                parameters.append("per_page", perPage.toString())
            }
        }.body()
    }

    suspend fun getRepo(id: Long): GithubRepoDto {
        return client.get("$BASE_URL/repositories/$id").body()
    }
}
