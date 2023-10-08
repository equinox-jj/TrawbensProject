package com.trawlbensproject.presentation.home

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.trawlbensproject.domain.entities.ResultItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class HomeState(
    val gameList: Flow<PagingData<ResultItems>> = emptyFlow(),
    val search: String? = null,
    val isDropDownExpanded: Boolean = false,
)