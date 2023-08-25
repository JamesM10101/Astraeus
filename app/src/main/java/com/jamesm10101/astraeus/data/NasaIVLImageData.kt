package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaIVLImageData(
    @Json(name = "center") val center: String,
    @Json(name = "title") val title: String,
    @Json(name = "photographer") val photographer: String?,
    @Json(name = "secondary_creator") val secondaryCreator: String?,
    @Json(name = "keywords") val keywords: List<String>?,
    @Json(name = "location") val location: String?,
    @Json(name = "nasa_id") val nasaId: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "date_created") val dateCreated: String,
    @Json(name = "description") val description: String,
    @Json(name = "description_508") val description508: String?,
) : Parcelable
