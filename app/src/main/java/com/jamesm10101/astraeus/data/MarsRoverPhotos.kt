package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRoverPhotos(
    @Json(name = "photos") val photos: List<MarsRoverPhoto>
)
