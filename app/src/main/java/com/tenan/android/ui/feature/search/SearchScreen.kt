package com.tenan.android.ui.feature.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.R
import com.tenan.android.data.source.fake.FakeCity
import com.tenan.android.data.source.fake.FakeRecentSearch
import com.tenan.android.entity.City
import com.tenan.android.ui.UiLoadState
import com.tenan.android.ui.component.ItemRecentSearch
import com.tenan.android.ui.component.ItemTourismCity
import com.tenan.android.ui.theme.ForestGreen50
import com.tenan.android.ui.theme.ForestGreen800
import com.tenan.android.ui.theme.ForestGreen900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateToResult: (query: String) -> Unit,
) {
    SearchScreenUi(
        onSearchAction = { onNavigateToResult(it) }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun SearchScreenUi(
    modifier: Modifier = Modifier,
    onSearchAction: (String) -> Unit
) {

    var query by remember { mutableStateOf("") }

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
                text = query,
                onTextChanged = { query = it },
                onSearchAction = { onSearchAction(query) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RecentSearchesSection(
                recentSearchUiLoadState = UiLoadState.Available(FakeRecentSearch.items),
                onClickRecentItem = onSearchAction
            )
        }
        item {
            TourismCitySection(
                tourismCityUiLoadState = UiLoadState.Available(FakeCity.items),
                onClickCityItem = onSearchAction
            )
        }
    }
}

@Composable
private fun SearchTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchAction: () -> Unit,
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
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchAction() }
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
private fun RecentSearchesSection(
    modifier: Modifier = Modifier,
    recentSearchUiLoadState: UiLoadState<List<String>>,
    onClickRecentItem: (String) -> Unit = { }
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
        when (recentSearchUiLoadState) {
            is UiLoadState.Available -> {
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(count = 3),
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth(),
                    horizontalItemSpacing = 8.dp,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(recentSearchUiLoadState.data) { query ->
                        ItemRecentSearch(
                            text = query,
                            onItemClicked = { onClickRecentItem(query) }
                        )
                    }
                }
            }
            is UiLoadState.Empty -> Unit
            is UiLoadState.Failed -> Unit
            is UiLoadState.Loading -> Unit
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
    tourismCityUiLoadState: UiLoadState<List<City>>,
    onClickCityItem: (name: String) -> Unit = { }
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
        when (tourismCityUiLoadState) {
            is UiLoadState.Available -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    modifier = Modifier
                        .height(320.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = false
                ) {
                    items(tourismCityUiLoadState.data) { city ->
                        ItemTourismCity(
                            cityName = city.name,
                            cityImageId = city.image,
                            onItemClick = { onClickCityItem(city.name) }
                        )
                    }
                }
            }
            is UiLoadState.Empty -> Unit
            is UiLoadState.Failed -> Unit
            is UiLoadState.Loading -> Unit
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
    SearchScreenUi(
        onSearchAction = { }
    )
}

@Composable
@Preview
private fun SearchTextFieldPreview() {
    SearchTextField(
        text = "",
        onTextChanged = {},
        onSearchAction = { }
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun RecentSearchesSectionPreview() {
    RecentSearchesSection(
        recentSearchUiLoadState = UiLoadState.Available(FakeRecentSearch.items)
    )
}

@Composable
@Preview
private fun TourismCitySectionPreview() {
    TourismCitySection(
        tourismCityUiLoadState = UiLoadState.Available(FakeCity.items)
    )
}