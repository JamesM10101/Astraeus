package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVLMetadata(
    @Json(name = "total_hits") val totalHits: Int
)
