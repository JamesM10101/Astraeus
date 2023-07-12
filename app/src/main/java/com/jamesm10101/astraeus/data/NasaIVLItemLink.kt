package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVLItemLink(
    @Json(name = "href") val thumbnail: String,
    @Json(name = "rel") val rel: String,
    @Json(name = "render") val renderType: String,
)
