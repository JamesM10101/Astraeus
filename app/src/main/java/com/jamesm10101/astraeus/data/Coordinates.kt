package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
    @Json(name = "lat") val lat: Float,
    @Json(name = "lon") val lon: Float
) : Parcelable
