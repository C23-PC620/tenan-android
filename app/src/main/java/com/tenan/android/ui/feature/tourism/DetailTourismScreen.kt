package com.tenan.android.ui.feature.tourism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tenan.android.data.source.fake.FakeTourism
import com.tenan.android.entity.Tourism
import com.tenan.android.ui.LoadState
import com.tenan.android.ui.component.ItemTourismCategory
import com.tenan.android.ui.theme.EarthyBrown50
import com.tenan.android.ui.theme.ForestGreen200
import com.tenan.android.ui.theme.ForestGreen900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTourismScreen(
    onNavigateUp: () -> Unit = { }
) {
    DetailTourismUi(
        detailTourismLoadState = LoadState.Available(FakeTourism.items.first()),
        onNavigateUp = onNavigateUp
    )
}

@ExperimentalMaterial3Api
@Composable
private fun DetailTourismUi(
    detailTourismLoadState: LoadState<Tourism>,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = { }
) {
    when (detailTourismLoadState) {
        is LoadState.Available -> {
            AvailableDataContent(
                data = detailTourismLoadState.data,
                modifier = modifier,
                onNavigateUp = onNavigateUp
            )
        }
        is LoadState.Empty -> Unit
        is LoadState.Failed -> Unit
        is LoadState.Loading -> Unit
    }
}

@ExperimentalMaterial3Api
@Composable
private fun AvailableDataContent(
    data: Tourism,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = { }
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item { ImageSection(data = data, onNavigateUp = onNavigateUp) }
        item { TitleSection(tourism = data) }
        item { Divider(modifier = Modifier.fillMaxWidth(), color = ForestGreen200) }
        item { LocationSection(location = data.city) }
        item { Divider(modifier = Modifier.fillMaxWidth(), color = ForestGreen200) }
        data.description?.let { description ->
            item { DescriptionSection(description = description) }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun ImageSection(
    data: Tourism,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = { }
) {
    Box(modifier = modifier) {
        SubcomposeAsyncImage(
            model = data.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(EarthyBrown50)
                )
            },
            success = { state ->
                SubcomposeAsyncImageContent(
                    painter = state.painter
                )
            },
            contentScale = ContentScale.Crop
        )
        TopAppBar(
            title = { },
            modifier = Modifier.statusBarsPadding(),
            navigationIcon = {
                IconButton(
                    onClick = onNavigateUp,
                    modifier = Modifier.clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Composable
private fun TitleSection(
    tourism: Tourism,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = tourism.placeName,
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ItemTourismCategory(
                icon = Icons.Rounded.Category,
                text = tourism.category,
                cardBackground = EarthyBrown50
            )
            ItemTourismCategory(
                icon = Icons.Rounded.Star,
                text = tourism.rating.toString(),
                cardBackground = EarthyBrown50
            )
        }
    }
}

@Composable
private fun LocationSection(
    location: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.LocationOn,
            contentDescription = null,
            tint = ForestGreen900
        )
        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
private fun DetailTourismUiPreview() {
    DetailTourismUi(detailTourismLoadState = LoadState.Available(FakeTourism.items.first()))
}