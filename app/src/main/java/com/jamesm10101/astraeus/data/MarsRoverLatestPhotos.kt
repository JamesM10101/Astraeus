package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRoverLatestPhotos(
    @Json(name = "latest_photos") val photos: List<MarsRoverPhoto>
)
