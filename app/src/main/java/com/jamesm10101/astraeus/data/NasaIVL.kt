package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVL(
    @Json(name = "collection") val collection: NasaIVLImageCollection,
)
