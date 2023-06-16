package com.tenan.android.ui.feature.search.result

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.R
import com.tenan.android.data.source.fake.FakeHotel
import com.tenan.android.data.source.fake.FakeTourism
import com.tenan.android.ui.component.ItemHotelLarge
import com.tenan.android.ui.component.ItemTourismLarge
import com.tenan.android.ui.theme.ForestGreen50
import com.tenan.android.ui.theme.ForestGreen900
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchResultScreen() {
    SearchResultScreenUi()
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
private fun SearchResultScreenUi(modifier: Modifier = Modifier) {

    val pageState = rememberPagerState(0)
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Jakarta",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = ForestGreen50
                    )
                )
                TabRow(
                    selectedTabIndex = pageState.currentPage,
                    containerColor = ForestGreen50,
                    indicator = { position ->
                        if (pageState.currentPage < position.size) {
                            TabRowDefaults.Indicator(
                                modifier = Modifier
                                    .tabIndicatorOffset(position[pageState.currentPage]),
                                color = ForestGreen900
                            )
                        }
                    },
                    divider = { }
                ) {
                    ResultTabItem.values().forEachIndexed { index, item ->
                        Tab(
                            selected = pageState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pageState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = item.asTitle(),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            },
                            selectedContentColor = ForestGreen900,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }

            }
        }
    ) { innerPadding ->
        HorizontalPager(
            pageCount = 3,
            modifier = Modifier.fillMaxSize(),
            state = pageState,
            beyondBoundsPageCount = 1
        ) { position ->
            when (position) {
                ResultTabItem.TOURISM.ordinal -> TourismScreen(modifier = Modifier.padding(innerPadding))
                ResultTabItem.HOTEL.ordinal -> HotelScreen(modifier = Modifier.padding(innerPadding))
                ResultTabItem.STORY.ordinal -> StoryScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
private fun ResultTabItem.asTitle() =
    when (this) {
        ResultTabItem.TOURISM -> stringResource(id = R.string.str_tourism)
        ResultTabItem.HOTEL -> stringResource(id = R.string.str_hotel)
        ResultTabItem.STORY -> stringResource(id = R.string.str_story)
    }

@Composable
private fun TourismScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = FakeTourism.items,
            key = { it.tourismId }
        ) { tourism ->
            ItemTourismLarge(
                tourism = tourism
            )
        }
    }
}

@Composable
private fun HotelScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = FakeHotel.items,
            key = { it.lodgingId }
        ) { hotel ->
            ItemHotelLarge(
                hotel = hotel
            )
        }
    }
}

@Composable
private fun StoryScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
private fun SearchResultScreenUiPreview() {
    SearchResultScreenUi()
}