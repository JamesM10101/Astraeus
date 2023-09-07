package com.jamesm10101.astraeus.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * This class provides type converters for IVL Images.
 */
class IVLImageConverters {
    /**
     * Converts a list of keywords to a string.
     *
     * @param keywords The list of keywords to convert.
     * @return A string representation of the list of keywords.
     */
    @TypeConverter
    fun fromCoordinates(keywords: List<String>?): String {
        return keywords.toString()
    }

    /**
     * Converts a string to a list of keywords.
     *
     * @param keywords The string to convert.
     * @return A list of keywords.
     */
    @TypeConverter
    fun toCoordinates(keywords: String?): List<String> {
        if (keywords.isNullOrEmpty()) return emptyList()
        return keywords.substring(1, keywords.length - 1).split(", ")
    }
}


@Parcelize
@Entity(tableName = "ivl_images")
@TypeConverters(IVLImageConverters::class)
data class NasaIVLImageData(
    @PrimaryKey @Json(name = "nasa_id") val nasaId: String,
    @Json(name = "center") val center: String,
    @Json(name = "title") val title: String,
    @Json(name = "photographer") val photographer: String?,
    @Json(name = "secondary_creator") val secondaryCreator: String?,
    @Json(name = "keywords") val keywords: List<String>?,
    @Json(name = "location") val location: String?,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "date_created") val dateCreated: String,
    @Json(name = "description") val description: String,
    @Json(name = "description_508") val description508: String?,
) : Parcelable
