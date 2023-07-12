package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class CentroidCoordinates(
    @Json(name = "lat") val lat: Float,
    @Json(name = "lon") val long: Float
)
