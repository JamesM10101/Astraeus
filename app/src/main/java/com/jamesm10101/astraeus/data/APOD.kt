package com.jamesm10101.astraeus.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity(tableName = "apods")
@Parcelize
data class APOD(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Json(name = "url") val mediaSrcUrl: String,
    @Json(name = "hdurl") val imgSrcHDUrl: String?,
    @Json(name = "thumbnail_url") val thumbUrl: String?,
    @Json(name = "date") val date: String,
    @Json(name = "title") val title: String,
    @Json(name = "copyright") val copyright: String?,
    @Json(name = "explanation") val explanation: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
) : Parcelable
