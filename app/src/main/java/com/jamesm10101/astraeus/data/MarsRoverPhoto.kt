package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRoverPhoto(
    val id: Int,
    @Json(name = "sol") val sol: Int,
    @Json(name = "camera") val camera: MarsRoverCam,
    @Json(name = "img_src") val imgSrcUrl: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: MarsRover
)
