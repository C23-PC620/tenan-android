package com.tenan.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tenan.android.data.source.fake.FakeTourism
import com.tenan.android.entity.Tourism
import com.tenan.android.ui.theme.EarthyBrown100
import com.tenan.android.ui.theme.EarthyBrown50
import com.tenan.android.ui.theme.EarthyBrown500
import com.tenan.android.ui.theme.ForestGreen100

@Composable
fun ItemTourismSmall(
    modifier: Modifier = Modifier,
    tourism: Tourism,
    onItemClick: () -> Unit = { }
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .height(260.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ForestGreen100)
    ) {
        Column(
            modifier = Modifier.clickable { onItemClick() }
        ) {
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(EarthyBrown50),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Image will appear here!")
            }
            Column(
                modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ItemTourismCategory(
                        icon = Icons.Rounded.Category,
                        text = tourism.category
                    )
                    ItemTourismCategory(
                        icon = Icons.Rounded.Star,
                        text = tourism.rating.toString()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tourism.placeName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = tourism.city,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
@Preview
private fun ItemTourismSmallPreview() {
    ItemTourismSmall(
        tourism = FakeTourism.items.first()
    )
}

@Composable
private fun ItemTourismCategory(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text : String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = EarthyBrown500
        )
        Text(
            text = text,
            color = EarthyBrown500,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
@Preview
private fun ItemTourismCategoryPreview() {
    ItemTourismCategory(
        icon = Icons.Rounded.Category,
        text = "This is category text"
    )
}