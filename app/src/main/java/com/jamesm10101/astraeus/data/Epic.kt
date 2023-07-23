package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Epic(
    @Json(name = "identifier") val id: String,
    @Json(name = "caption") val caption: String,
    @Json(name = "image") val image: String,
    var imgSrcUrl: String?,
    @Json(name = "version") val version: String,
    @Json(name = "date") val date: String,
    @Json(name = "centroid_coordinates") val centroidCoords: Coordinates,
    @Json(name = "dscovr_j2000_position") val dscovrPos: SatellitePosition,
    @Json(name = "lunar_j2000_position") val lunarPos: SatellitePosition,
    @Json(name = "sun_j2000_position") val sunPos: SatellitePosition,
    @Json(name = "attitude_quaternions") val attQuaternions: AttitudeQuaternions,
) : Parcelable