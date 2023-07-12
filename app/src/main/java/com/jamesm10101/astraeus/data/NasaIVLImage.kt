package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVLImage(
    @Json(name = "href") val imgUrl: String,
    @Json(name = "data") val data: List<NasaIVLImageData>,
    @Json(name = "links") val links: List<NasaIVLItemLink>,
)
