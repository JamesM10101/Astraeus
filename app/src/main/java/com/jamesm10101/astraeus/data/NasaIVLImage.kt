package com.jamesm10101.astraeus.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

/**
 * This class provides type converters for IVL Images.
 */
class NasaIVLImageConverters {
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Converts a list of NasaIVLImageData to a string.
     *
     * @param list The list to convert.
     * @return A string representation of the list.
     */
    @TypeConverter
    fun fromNasaImageData(list: List<NasaIVLImageData>?): String {
        val listType = Types.newParameterizedType(List::class.java, NasaIVLImageData::class.java)
        val adapter: JsonAdapter<List<NasaIVLImageData>> = moshi.adapter(listType)
        return adapter.toJson(list)
    }

    /**
     * Coverts a string to a list of NasaIVlImageData.
     *
     * @param value The string to convert.
     * @return The list of IVL Image Data.
     */
    @TypeConverter
    fun toNasaImageDataList(value: String?): List<NasaIVLImageData>? {
        val listType = Types.newParameterizedType(List::class.java, NasaIVLImageData::class.java)
        val adapter: JsonAdapter<List<NasaIVLImageData>> = moshi.adapter(listType)
        return adapter.fromJson(value ?: "[]")
    }

    /**
     * Converts a list of NasaIVLImageLink to a string.
     *
     * @param list The list to convert.
     * @return A string representation of the list.
     */
    @TypeConverter
    fun fromNasaIVLItemLink(list: List<NasaIVLItemLink>?): String {
        val listType = Types.newParameterizedType(List::class.java, NasaIVLItemLink::class.java)
        val adapter: JsonAdapter<List<NasaIVLItemLink>> = moshi.adapter(listType)
        return adapter.toJson(list)
    }

    /**
     * Converts a string to a list of NasaIVLItemLink.
     *
     * @param value The string to convert.
     * @return The list of NasaIVLItemLink.
     */
    @TypeConverter
    fun toNasaIVlItemLink(value: String?): List<NasaIVLItemLink>? {
        val listType = Types.newParameterizedType(List::class.java, NasaIVLItemLink::class.java)
        val adapter: JsonAdapter<List<NasaIVLItemLink>> = moshi.adapter(listType)
        return adapter.fromJson(value ?: "[]")
    }

}

@Parcelize
@Entity(tableName = "ivl_images")
@TypeConverters(NasaIVLImageConverters::class)
data class NasaIVLImage(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @Json(name = "href") val imgUrl: String,
    @Json(name = "data") val data: List<NasaIVLImageData>,
    @Json(name = "links") val links: List<NasaIVLItemLink>,
) : Parcelable
