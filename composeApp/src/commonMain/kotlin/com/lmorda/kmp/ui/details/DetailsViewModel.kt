package com.lmorda.kmp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.kmp.domain.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val SAVED_ID_KEY = "id"

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val id: Long? = savedStateHandle[SAVED_ID_KEY]

    private val _state = MutableStateFlow(DetailsContract.State())
    val state: StateFlow<DetailsContract.State> = _state

    init {
        id?.let { repoId ->
            viewModelScope.launch {
                try {
                    val githubRepo = dataRepository.getRepo(id = repoId)
                    _state.value = state.value.copy(
                        githubRepo = githubRepo,
                    )
                } catch (ex: Exception) {
                    _state.value = state.value.copy(
                        exception = ex
                    )
                }
            }
        }
    }
}
