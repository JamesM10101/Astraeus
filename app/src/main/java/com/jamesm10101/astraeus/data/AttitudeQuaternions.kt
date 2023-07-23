package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttitudeQuaternions(
    @Json(name = "q0") val q0: Float,
    @Json(name = "q1") val q1: Float,
    @Json(name = "q2") val q2: Float,
    @Json(name = "q3") val q3: Float
) : Parcelable
