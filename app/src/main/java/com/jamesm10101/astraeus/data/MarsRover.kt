package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRover(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "landing_date") val landingDate: String,
    @Json(name = "launch_date") val launchDate: String,
    @Json(name = "status") val status: String,
    @Json(name = "max_sol") val maxSol: Int,
    @Json(name = "max_date") val maxDate: String,
    @Json(name = "total_photos") val totalPhotos: Int,
    @Json(name = "cameras") val cameras: List<MarsRoverCam>
)