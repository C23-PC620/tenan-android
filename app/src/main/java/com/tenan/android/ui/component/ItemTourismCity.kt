package com.tenan.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tenan.android.R
import com.tenan.android.data.source.fake.FakeCity
import com.tenan.android.ui.theme.EarthyBrown50
import com.tenan.android.ui.theme.ForestGreen100

@Composable
fun ItemTourismCity(
    cityName: String,
    cityImageId: Int,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = { }
) {
    Card(
        modifier = modifier.size(156.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onItemClick() }
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
                painter = painterResource(id = cityImageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
            )
            Text(
                text = cityName,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
@Preview
private fun ItemTourismCityPreview() {
    ItemTourismCity(
        cityName = FakeCity.items.first().name,
        cityImageId = FakeCity.items.first().image
    )
}