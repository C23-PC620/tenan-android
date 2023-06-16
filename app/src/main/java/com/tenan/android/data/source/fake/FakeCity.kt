package com.tenan.android.data.source.fake

import com.tenan.android.R
import com.tenan.android.entity.City

object FakeCity {

    val items = listOf(
        City(
            name = "Jakarta",
            image = R.drawable.tourism_jakarta_monas
        ),
        City(
            name = "Surabaya",
            image = R.drawable.tourism_surabaya
        ),
        City(
            name = "Bandung",
            image = R.drawable.tourism_bandung_gedung_sate
        ),
        City(
            name = "Bali",
            image = R.drawable.tourism_bali
        )
    )

}