package com.trawlbensproject.presentation.detail

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.trawlbens.core.helper.parentPlatformIcon
import org.jsoup.Jsoup

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreen(
    state: DetailState,
    onEvent: (DetailEvent) -> Unit,
) {
    val data = state.gameDetail
    val scrollState = rememberScrollState()
    val textAnimate by animateIntAsState(
        targetValue = if (state.isTextExpanded) 500 else 3,
        label = "Text Expanded Animation",
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 100,
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (!state.isLoading && state.errorMessage == null) {
                if (!state.isGameSaved) {
                    SmallFloatingActionButton(
                        onClick = {
                            onEvent(DetailEvent.OnFavoriteClicked(data = data))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                        )
                    }
                } else {
                    SmallFloatingActionButton(
                        onClick = {
                            onEvent(DetailEvent.OnDeleteFavoriteClicked(data = data))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite Icon",
                            tint = Color.Red,
                        )
                    }
                }
            }
        }
    ) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.errorMessage?.isNotEmpty() == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.errorMessage,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        onEvent(DetailEvent.OnGameByIdFetched)
                        onEvent(DetailEvent.OnGameScreenshotsFetched)
                    }
                ) {
                    Text(text = "Retry")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
                    .verticalScroll(state = scrollState)
            ) {
                AsyncImage(
                    model = data?.background,
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = data?.gameTitle.orEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Release Date",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = data?.released.orEmpty(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Publisher",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = data?.publishers?.get(0)?.name.orEmpty(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "Rating",
                                    fontSize = 12.sp,
                                )
                                Text(
                                    text = data?.rating.toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                data?.parentPlatforms?.forEach { parentPlatform ->
                                    val platformName = parentPlatform.platform?.name.orEmpty()

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
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Meta Score",
                                fontSize = 12.sp,
                            )
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        width = 4.dp,
                                        color = Color.Green,
                                        shape = RoundedCornerShape(10.dp)
                                    ), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = data?.metaScore.toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Green
                                )
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Genre",
                                fontSize = 12.sp,
                            )
                            FlowRow(
                                maxItemsInEachRow = 2,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                data?.genres.orEmpty().forEach { genreItem ->
                                    ElevatedAssistChip(
                                        onClick = {},
                                        label = {
                                            Text(
                                                text = genreItem.genreName.orEmpty(),
                                                fontSize = 10.sp
                                            )
                                        },
                                        modifier = Modifier.wrapContentSize(),
                                        shape = RoundedCornerShape(20.dp),
                                        enabled = false,
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Description",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = Jsoup.parse(data?.description.orEmpty()).text(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    fontSize = 14.sp,
                    maxLines = textAnimate,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(onClick = { onEvent(DetailEvent.OnTextExpandedClicked(isTextExpanded = true)) }) {
                        Text(text = "SHOW")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "|")
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = { onEvent(DetailEvent.OnTextExpandedClicked(isTextExpanded = false)) }) {
                        Text(text = "HIDE")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Screenshots",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(items = state.gameScreenShots) { screenshotItems ->
                        AsyncImage(
                            model = screenshotItems.image,
                            contentDescription = "Screenshot Image",
                            modifier = Modifier
                                .height(200.dp)
                                .width(300.dp)
                                .clip(shape = RoundedCornerShape(15.dp)),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}