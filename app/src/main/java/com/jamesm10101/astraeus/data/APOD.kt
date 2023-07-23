package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class APOD(
    @Json(name = "url") val imgSrcUrl: String,
    @Json(name = "hdurl") val imgSrcHDUrl: String,
    @Json(name = "date") val date: String,
    @Json(name = "title") val title: String,
    @Json(name = "copyright") val copyright: String?,
    @Json(name = "explanation") val explanation: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
) : Parcelable
