package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaIVLImageCollection(
    @Json(name = "version") val version: String,
    @Json(name = "href") val selfUrl: String,
    @Json(name = "items") val images: List<NasaIVLImage>,
    @Json(name = "metadata") val metadata: NasaIVLMetadata,
    @Json(name = "links") val pageLinks: List<NasaIVLImageCollectionLink>?,
) : Parcelable
