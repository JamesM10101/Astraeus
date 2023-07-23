package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsRoverPhoto(
    val id: Int,
    @Json(name = "sol") val sol: Int,
    @Json(name = "camera") val camera: MarsRoverCam,
    @Json(name = "img_src") val imgSrcUrl: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: MarsRover
) : Parcelable