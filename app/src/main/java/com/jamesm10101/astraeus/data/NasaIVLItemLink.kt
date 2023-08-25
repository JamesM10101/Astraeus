package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaIVLItemLink(
    @Json(name = "href") val thumbnail: String,
    @Json(name = "rel") val rel: String,
    @Json(name = "render") val renderType: String,
) : Parcelable
