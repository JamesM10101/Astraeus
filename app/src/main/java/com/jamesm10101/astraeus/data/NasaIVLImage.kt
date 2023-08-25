package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaIVLImage(
    @Json(name = "href") val imgUrl: String,
    @Json(name = "data") val data: List<NasaIVLImageData>,
    @Json(name = "links") val links: List<NasaIVLItemLink>,
) : Parcelable
