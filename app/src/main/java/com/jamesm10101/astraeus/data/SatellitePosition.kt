package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class SatellitePosition(
    @Json(name = "x") val x: Float,
    @Json(name = "y") val y: Float,
    @Json(name = "z") val z: Float,
)