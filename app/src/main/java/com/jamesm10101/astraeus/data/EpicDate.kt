package com.jamesm10101.astraeus.data

import com.squareup.moshi.Json

data class EpicDate(
    @Json(name = "date") val date: String
)
