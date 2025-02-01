package com.lmorda.kmp.ui.details

import com.lmorda.kmp.domain.model.GithubRepo

sealed interface DetailsContract {

    data class State(
        val githubRepo: GithubRepo? = null,
        val exception: Exception? = null,
    )

}
