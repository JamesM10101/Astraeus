package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class MarsRoverDetails(
    @Json(name = "rover") val rover: MarsRover
)
