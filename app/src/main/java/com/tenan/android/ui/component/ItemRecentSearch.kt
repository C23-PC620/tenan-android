@file:OptIn(ExperimentalMaterial3Api::class)

package com.tenan.android.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.ui.theme.ForestGreen800

@ExperimentalMaterial3Api
@Composable
fun ItemRecentSearch(
    text: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onItemClicked() },
        modifier = modifier.wrapContentWidth().wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = ForestGreen800
        ),
        border = BorderStroke(
            width = 1.dp,
            color = ForestGreen800
        )
    ) {
        Text(
            text = text,
            modifier = modifier
                .padding(12.dp)
                .align(CenterHorizontally),
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
@Preview
private fun ItemRecentSearchPreview() {
    ItemRecentSearch(
        text = "Kuliner di Bandung",
        onItemClicked = { }
    )
}