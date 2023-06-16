package com.tenan.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.ui.theme.EarthyBrown500

@Composable
fun ItemTourismCategory(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text : String,
    cardBackground: Color = MaterialTheme.colorScheme.surface
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(cardBackground)
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
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
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