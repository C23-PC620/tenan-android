package com.tenan.android.ui.feature.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.R
import com.tenan.android.data.source.fake.FakeCity
import com.tenan.android.data.source.fake.FakeRecentSearch
import com.tenan.android.entity.City
import com.tenan.android.ui.LoadState
import com.tenan.android.ui.component.ItemRecentSearch
import com.tenan.android.ui.component.ItemTourismCity
import com.tenan.android.ui.theme.ForestGreen50
import com.tenan.android.ui.theme.ForestGreen800
import com.tenan.android.ui.theme.ForestGreen900
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    SearchScreenUi()
}

@ExperimentalMaterial3Api
@Composable
private fun SearchScreenUi(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.str_searching),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = stringResource(id = R.string.str_searching_message),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        item {
            SearchTextField(
                text = "",
                onTextChanged = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RecentSearchesSection(
                recentSearchLoadState = LoadState.Available(FakeRecentSearch.items)
            )
        }
        item {
            TourismCitySection(
                tourismCityLoadState = LoadState.Available(FakeCity.items)
            )
        }
    }
}

@Composable
private fun SearchTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.str_msg_search_hint)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = ForestGreen900
            )
        },
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = ForestGreen50,
            unfocusedContainerColor = ForestGreen50,
            focusedBorderColor = ForestGreen50,
            unfocusedBorderColor = ForestGreen50
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
private fun RecentSearchesSection(
    modifier: Modifier = Modifier,
    recentSearchLoadState: LoadState<List<String>>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.str_recent_search),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(0.dp))
        when (recentSearchLoadState) {
            is LoadState.Available -> {
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(count = 3),
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth(),
                    horizontalItemSpacing = 8.dp,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(recentSearchLoadState.data) { query ->
                        ItemRecentSearch(
                            text = query,
                            onItemClicked = { }
                        )
                    }
                }
            }
            is LoadState.Empty -> Unit
            is LoadState.Failed -> Unit
            is LoadState.Loading -> Unit
        }
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForestGreen800,
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = R.string.str_other)
            )
        }
    }
}

@Composable
private fun TourismCitySection(
    modifier: Modifier = Modifier,
    tourismCityLoadState: LoadState<List<City>>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.str_popular_city),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(0.dp))
        when (tourismCityLoadState) {
            is LoadState.Available -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    modifier = Modifier
                        .height(320.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(tourismCityLoadState.data) { city ->
                        ItemTourismCity(
                            cityName = city.name,
                            cityImageId = city.image
                        )
                    }
                }
            }
            is LoadState.Empty -> Unit
            is LoadState.Failed -> Unit
            is LoadState.Loading -> Unit
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
private fun SearchScreenUiPreview() {
    SearchScreenUi()
}

@Composable
@Preview
private fun SearchTextFieldPreview() {
    SearchTextField(
        text = "",
        onTextChanged = {})
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun RecentSearchesSectionPreview() {
    RecentSearchesSection(
        recentSearchLoadState = LoadState.Available(FakeRecentSearch.items)
    )
}

@Composable
@Preview
private fun TourismCitySectionPreview() {
    TourismCitySection(
        tourismCityLoadState = LoadState.Available(FakeCity.items)
    )
}