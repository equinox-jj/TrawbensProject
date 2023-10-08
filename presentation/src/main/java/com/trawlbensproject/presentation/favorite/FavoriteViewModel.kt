package com.trawlbensproject.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.usecase.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val gameUseCase: GameUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    init {
        onEvent(FavoriteEvent.ShowFavoriteGameList)
    }

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            FavoriteEvent.ShowFavoriteGameList -> {
                getFavoriteList()
            }

            is FavoriteEvent.OnDeleteAllClicked -> {
                _state.update {
                    it.copy(isDialogShowed = event.showDialog)
                }

            }

            FavoriteEvent.OnDeleteAllDialogClicked -> {
                deleteAllFavoriteGames()
            }

            is FavoriteEvent.OnDropdownMenuClicked -> {
                _state.update {
                    it.copy(isDropDownExpanded = event.expanded)
                }
            }

            is FavoriteEvent.OnDeleteSingleItemClicked -> {
                deleteFavoriteGame(data = event.data)
            }

        }
    }

    private fun getFavoriteList() {
        viewModelScope.launch {
            gameUseCase.getFavoriteGames().collect { result ->
                _state.update {
                    it.copy(favoriteItems = result)
                }
            }
        }
    }

    private fun deleteAllFavoriteGames() {
        viewModelScope.launch {
            gameUseCase.deleteAllFavoriteGames()
        }
    }

    private fun deleteFavoriteGame(data: GameDetail) {
        viewModelScope.launch {
            gameUseCase.deleteFavoriteGame(data = data)
        }
    }
}