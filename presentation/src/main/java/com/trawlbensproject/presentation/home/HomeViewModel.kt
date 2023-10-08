package com.trawlbensproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.trawlbensproject.domain.usecase.GameUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val gameUseCase: GameUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        onEvent(HomeEvent.OnGamePagingFetched)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnGamePagingFetched -> {
                getGameList()
            }

            is HomeEvent.OnDropdownMenuClicked -> {
                _state.update {
                    it.copy(isDropDownExpanded = event.expanded)
                }
            }

            is HomeEvent.OnSearchQueryTextChanged -> {
                _state.update {
                    it.copy(search = event.query)
                }
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    getGameList()
                }
            }
        }
    }

    private fun getGameList() {
        _state.update {
            it.copy(
                gameList = gameUseCase.getGameList(_state.value.search)
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
            )
        }
    }
}