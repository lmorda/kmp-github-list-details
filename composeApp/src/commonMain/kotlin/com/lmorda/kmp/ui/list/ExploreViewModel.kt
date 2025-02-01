package com.lmorda.kmp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.kmp.domain.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ExploreContract.State(isFirstLoad = true))
    val state: StateFlow<ExploreContract.State> = _state

    init {
        getNextPage()
    }

    fun onRefresh() {
        _state.value = ExploreContract.State(isRefreshing = true)
        getNextPage()
    }

    fun getNextPage() {
        viewModelScope.launch {
            try {
                if (state.value.page > 0) {
                    _state.update { it.copy(isFetchingNextPage = true) }
                }
                val repos = dataRepository.getRepos(page = state.value.page + 1)
                _state.update {
                    it.hideLoading().copy(
                        githubRepos = it.githubRepos + repos,
                        page = state.value.page + 1,
                    )
                }
            } catch (ex: Exception) {
                _state.update { it.hideLoading().copy(exception = ex) }
            }
        }
    }
}
