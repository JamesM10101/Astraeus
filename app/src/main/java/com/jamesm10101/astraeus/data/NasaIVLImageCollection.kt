package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class NasaIVLImageCollection(
    @Json(name = "version") val version: String,
    @Json(name = "href") val selfUrl: String,
    @Json(name = "items") val images: List<NasaIVLImage>,
    @Json(name = "metadata") val metadata: NasaIVLMetadata,
    @Json(name = "links") val pageLinks: List<NasaIVLImageCollectionLink>?,
)
