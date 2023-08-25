package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaIVL(
    @Json(name = "collection") val collection: NasaIVLImageCollection,
) : Parcelable
