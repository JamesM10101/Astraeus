package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class Epic(
    @Json(name = "identifier") val id: String,
    @Json(name = "caption") val caption: String,
    @Json(name = "image") val image: String,
    @Json(name = "version") val version: String,
    @Json(name = "date") val date: String,
    @Json(name = "centroid_coordinates") val centroidCoords: CentroidCoordinates,
    @Json(name = "dscovr_j2000_position") val dscovrPos: SatellitePosition,
    @Json(name = "lunar_j2000_position") val lunarPos: SatellitePosition,
    @Json(name = "sun_j2000_position") val sunPos: SatellitePosition,
    @Json(name = "attitude_quaternions") val attQuaternions: AttitudeQuaternions,
)