package com.jamesm10101.astraeus.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsRoverPhotos(
    @Json(name = "photos") val photos: List<MarsRoverPhoto>
) : Parcelable
