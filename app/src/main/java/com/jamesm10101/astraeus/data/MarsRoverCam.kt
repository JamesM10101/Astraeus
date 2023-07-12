package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRoverCam(
    val id: Int?,
    @Json(name = "name") val name: String,
    @Json(name = "rover_id") val roverId: String?,
    @Json(name = "full_name") val fullName: String,
)

