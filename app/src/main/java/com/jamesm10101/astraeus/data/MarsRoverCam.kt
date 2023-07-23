package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsRoverCam(
    val id: Int?,
    @Json(name = "name") val name: String,
    @Json(name = "rover_id") val roverId: String?,
    @Json(name = "full_name") val fullName: String,
) : Parcelable