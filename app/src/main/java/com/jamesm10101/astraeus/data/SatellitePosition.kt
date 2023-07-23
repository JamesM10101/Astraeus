package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatellitePosition(
    @Json(name = "x") val x: Float,
    @Json(name = "y") val y: Float,
    @Json(name = "z") val z: Float,
) : Parcelable