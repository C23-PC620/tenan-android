package com.tenan.android.ui.feature.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import com.tenan.android.R
import com.tenan.android.data.source.fake.FakeTourism
import com.tenan.android.entity.Tourism
import com.tenan.android.ui.UiLoadState
import com.tenan.android.ui.component.ItemTourismSmall

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val selectedTourismState by viewModel.recommendedTourismUiState.observeAsState(UiLoadState.Loading())
    ExploreScreenUi(
        selectedTourismState = selectedTourismState
    )
}

@Composable
private fun ExploreScreenUi(
    modifier: Modifier = Modifier,
    selectedTourismState: UiLoadState<List<Tourism>>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.tenan_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(96.dp)
                    .wrapContentWidth()
                    .padding(16.dp),
                contentScale = ContentScale.FillHeight
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.str_selected_tourism_place),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        when (selectedTourismState) {
            is UiLoadState.Loading -> Unit
            is UiLoadState.Empty -> Unit
            is UiLoadState.Failed -> Unit
            is UiLoadState.Available -> {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = selectedTourismState.data,
                            key = { it.tourismId }
                        ) { tourism ->
                            ItemTourismSmall(
                                tourism = tourism
                            )
                        }
                    }
                }
            }
        }
        item {
            ExploreBanner(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.str_favorite_tourism_place),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        when (selectedTourismState) {
            is UiLoadState.Loading -> Unit
            is UiLoadState.Empty -> Unit
            is UiLoadState.Failed -> Unit
            is UiLoadState.Available -> {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = selectedTourismState.data,
                            key = { it.tourismId }
                        ) { tourism ->
                            ItemTourismSmall(
                                tourism = tourism
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
private fun ExploreBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        val contrast = 1f // 0f..10f (1 should be default)
        val brightness = -95f // -255f..255f (0 should be default)
        val colorMatrix = floatArrayOf(
            contrast, 0f, 0f, 0f, brightness,
            0f, contrast, 0f, 0f, brightness,
            0f, 0f, contrast, 0f, brightness,
            0f, 0f, 0f, 1f, 0f
        )
        Image(
            painter = painterResource(id = R.drawable.explore_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(120.dp),
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
        )
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.str_msg_to_turn_on_location),
                modifier = Modifier.weight(1f),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ExploreScreenUiPreview() {
    ExploreScreenUi(
        selectedTourismState = UiLoadState.Available(FakeTourism.items)
    )
}

@Composable
@Preview
private fun ExploreBannerPreview() {
    ExploreBanner()
}