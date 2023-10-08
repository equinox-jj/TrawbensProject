package com.trawlbensproject.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.trawlbens.core.helper.parentPlatformIcon
import com.trawlbensproject.domain.entities.ResultItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    lazyPagingItems: LazyPagingItems<ResultItems>,
    onEvent: (HomeEvent) -> Unit,
    navigateToDetail: (Int) -> Unit,
    navigateToFavorite: () -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "GameList") },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(HomeEvent.OnDropdownMenuClicked(!state.isDropDownExpanded))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "Drop Down Menu Icon"
                        )
                    }

                    DropdownMenu(
                        expanded = state.isDropDownExpanded,
                        onDismissRequest = {
                            onEvent(HomeEvent.OnDropdownMenuClicked(!state.isDropDownExpanded))
                        },
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Favorite") },
                            onClick = {
                                navigateToFavorite()
                                onEvent(HomeEvent.OnDropdownMenuClicked(!state.isDropDownExpanded))
                            }
                        )
                    }
                },
                scrollBehavior = scrollBehaviour
            )
        },
        floatingActionButton = {
            AnimatedVisibility(visible = showButton, enter = fadeIn(), exit = fadeOut()) {
                SmallFloatingActionButton(
                    onClick = {
                        scope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = "Top Button"
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                value = state.search.orEmpty(),
                onValueChange = { onEvent(HomeEvent.OnSearchQueryTextChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp,
                    ),
                placeholder = {
                    Text(text = "Search")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.TwoTone.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done,
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
                state = lazyListState,
            ) {
                pagingItems(
                    lazyPagingItems = lazyPagingItems,
                    navigateToDetail = navigateToDetail,
                )
                pagingAppendLoadState(
                    lazyPagingItems = lazyPagingItems,
                    onRetryClicked = { onEvent(HomeEvent.OnGamePagingFetched) }
                )
                pagingRefreshLoadState(
                    lazyPagingItems = lazyPagingItems,
                    onRetryClicked = { onEvent(HomeEvent.OnGamePagingFetched) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun LazyListScope.pagingItems(
    lazyPagingItems: LazyPagingItems<ResultItems>,
    navigateToDetail: (Int) -> Unit,
) {
    items(
        count = lazyPagingItems.itemCount,
        key = { lazyPagingItems[it]?.id!! }
    ) { index ->
        val data = lazyPagingItems[index]

        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navigateToDetail(data?.id!!) },
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
        ) {
            AsyncImage(
                model = data?.background,
                contentDescription = "Game Background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                data?.parentPlatforms?.forEach {
                    val platformName = it.platform?.name.orEmpty()

                    if (platformName == "PC" || platformName == "PlayStation" || platformName == "Xbox" || platformName == "SEGA") {
                        Icon(
                            painter = painterResource(id = parentPlatformIcon(platformName)),
                            contentDescription = "Platform Icon"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data?.gameTitle.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

private fun LazyListScope.pagingAppendLoadState(
    lazyPagingItems: LazyPagingItems<ResultItems>,
    onRetryClicked: () -> Unit,
) {
    when (val loadState = lazyPagingItems.loadState.append) {
        LoadState.Loading -> {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        is LoadState.Error -> {
            val errorMessage = loadState.error.message
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = errorMessage ?: "An unknown error occurred.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRetryClicked) {
                        Text(text = "Retry")
                    }
                }
            }
        }

        else -> {}
    }
}

private fun LazyListScope.pagingRefreshLoadState(
    lazyPagingItems: LazyPagingItems<ResultItems>,
    onRetryClicked: () -> Unit,
) {
    when (val loadState = lazyPagingItems.loadState.refresh) {
        LoadState.Loading -> {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        is LoadState.Error -> {
            val errorMessage = loadState.error.message
            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = errorMessage ?: "An unknown error occurred.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRetryClicked) {
                        Text(text = "Retry")
                    }
                }
            }
        }

        else -> {}
    }
}