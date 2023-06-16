package com.tenan.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tenan.android.data.source.fake.FakeStory
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.Story
import com.tenan.android.ui.theme.EarthyBrown50
import com.tenan.android.ui.theme.ForestGreen200

@Composable
fun ItemStory(
    modifier: Modifier = Modifier,
    story: Story,
    onItemClick: () -> Unit = { }
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .clickable { onItemClick() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = story.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = story.date,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = story.author,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            SubcomposeAsyncImage(
                model = story.image,
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(16.dp)),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .background(EarthyBrown50),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Loading image")
                    }
                },
                success = { state ->
                    SubcomposeAsyncImageContent(
                        painter = state.painter
                    )
                },
                contentScale = ContentScale.Crop
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = ForestGreen200
        )
    }
}

@Composable
@Preview
private fun ItemStoryPreview() {
    ItemStory(
        story = FakeStory.items.first(),
    )
}