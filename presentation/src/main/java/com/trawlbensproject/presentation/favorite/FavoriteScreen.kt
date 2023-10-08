package com.trawlbensproject.presentation.favorite

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.trawlbens.core.helper.parentPlatformIcon
import com.trawlbensproject.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    state: FavoriteState,
    onEvent: (FavoriteEvent) -> Unit,
    popBackStack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_data))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Favorite Game List") },
                actions = {
                    IconButton(onClick = { onEvent(FavoriteEvent.OnDeleteAllClicked(showDialog = !state.isDialogShowed)) }) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete Icon"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back Icon",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        AnimatedVisibility(
            visible = state.isDialogShowed,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AlertDialog(
                title = {
                    Text(text = "Are you sure you want to delete all games from favorite?")
                },
                text = {
                    Text(text = "This action cannot be undone.")
                },
                onDismissRequest = { onEvent(FavoriteEvent.OnDeleteAllClicked(showDialog = !state.isDialogShowed)) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onEvent(FavoriteEvent.OnDeleteAllDialogClicked)
                            onEvent(FavoriteEvent.OnDeleteAllClicked(showDialog = !state.isDialogShowed))
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onEvent(FavoriteEvent.OnDeleteAllClicked(showDialog = !state.isDialogShowed)) }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
        if (state.favoriteItems.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                Text(
                    text = "Empty Data.",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    items(
                        items = state.favoriteItems,
                        key = { it.id ?: 0 }
                    ) { data ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { navigateToDetail(data.id ?: 0) },
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                            ) {
                                AsyncImage(
                                    model = data.background,
                                    contentDescription = "Game Background",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.FillBounds,
                                )
                                FilledIconButton(
                                    onClick = { onEvent(FavoriteEvent.OnDeleteSingleItemClicked(data)) },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(end = 12.dp, top = 12.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Favorite,
                                        contentDescription = "Favorite Icon",
                                        tint = Color.Red,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                data.parentPlatforms.forEach {
                                    val platformName = it.platform?.name.orEmpty()

                                    if (platformName == "PC" || platformName == "PlayStation" || platformName == "Xbox" || platformName == "SEGA") {
                                        Icon(
                                            painter = painterResource(
                                                id = parentPlatformIcon(
                                                    platformName
                                                )
                                            ),
                                            contentDescription = "Platform Icon"
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = data.gameTitle.orEmpty(),
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
            }
        }
    }
}