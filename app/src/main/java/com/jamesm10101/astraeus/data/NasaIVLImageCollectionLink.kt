package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVLImageCollectionLink(
    @Json(name = "rel") val rel: String,
    @Json(name = "prompt") val prompt: String,
    @Json(name = "href") val url: String,
)
