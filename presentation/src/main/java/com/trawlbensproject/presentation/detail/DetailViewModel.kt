package com.trawlbensproject.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trawlbens.core.Resource
import com.trawlbensproject.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gameUseCase: GameUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val gameId = savedStateHandle.getStateFlow("gameId", 0)

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        onEvent(DetailEvent.OnGameByIdFetched)
        onEvent(DetailEvent.OnGameScreenshotsFetched)
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnGameByIdFetched -> {
                if (gameUseCase.isGameListedInFavorite(gameId.value)) {
                    _state.update {
                        it.copy(isGameSaved = true)
                    }
                }
                getGameById()
            }

            DetailEvent.OnGameScreenshotsFetched -> {
                getGameScreenshots()
            }

            is DetailEvent.OnFavoriteClicked -> {
                viewModelScope.launch {
                    event.data?.let { gameUseCase.insertFavoriteGame(data = it) }
                    _state.update {
                        it.copy(isGameSaved = true)
                    }
                }
            }

            is DetailEvent.OnDeleteFavoriteClicked -> {
                viewModelScope.launch {
                    event.data?.let { gameUseCase.deleteFavoriteGame(data = it) }
                    _state.update {
                        it.copy(isGameSaved = false)
                    }
                }
            }

            is DetailEvent.OnTextExpandedClicked -> {
                _state.update {
                    it.copy(isTextExpanded = event.isTextExpanded)
                }
            }
        }
    }

    private fun getGameById() {
        viewModelScope.launch {
            gameUseCase.getGameById(gameId.value).collect { resource ->
                when (resource) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                gameDetail = null,
                                errorMessage = null,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                gameDetail = resource.data,
                                errorMessage = null,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                gameDetail = null,
                                errorMessage = resource.message,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getGameScreenshots() {
        viewModelScope.launch {
            gameUseCase.getGameScreenshots(gameId.value).collect { resource ->
                when (resource) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                gameScreenShots = emptyList(),
                                errorMessage = null,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                gameScreenShots = resource.data?.results ?: emptyList(),
                                errorMessage = null,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                gameScreenShots = emptyList(),
                                errorMessage = resource.message,
                            )
                        }
                    }
                }
            }
        }
    }
}