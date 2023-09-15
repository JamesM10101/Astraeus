package com.jamesm10101.astraeus.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

/**
 * This class provides type converters for Mars Rover Photos.
 */
class MarsRoverPhotoConverters {
    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Converts a Mars Rover Photo to a JSON string.
     *
     * @param photo The Mars Rover Photo to convert.
     * @return A JSON string representation of the Mars Rover Photo.
     */
    @TypeConverter
    fun fromMarsRoverPhoto(photo: MarsRoverPhoto): String {
        val adapter = moshi.adapter(MarsRoverPhoto::class.java)
        return adapter.toJson(photo)
    }

    /**
     * Converts a JSON string to a Mars Rover Photo.
     *
     * @param json The JSON string to convert.
     * @return A Mars Rover Photo object.
     */
    @TypeConverter
    fun toMarsRoverPhoto(json: String): MarsRoverPhoto {
        val adapter = moshi.adapter(MarsRoverPhoto::class.java)
        return adapter.fromJson(json)!!
    }

    /**
     * Converts a Mars Rover to a JSON string.
     *
     * @param rover The Mars Rover to convert.
     * @return A JSON string representation of the Mars Rover.
     */
    @TypeConverter
    fun fromMarsRover(rover: MarsRover): String {
        val adapter = moshi.adapter(MarsRover::class.java)
        return adapter.toJson(rover)
    }

    /**
     * Converts a JSON string to a Mars Rover.
     *
     * @param json The JSON string to convert.
     * @return A Mars Rover object.
     */
    @TypeConverter
    fun toMarsRover(json: String): MarsRover {
        val adapter = moshi.adapter(MarsRover::class.java)
        return adapter.fromJson(json)!!
    }

    /**
     * Converts a Mars Rover Cam to a JSON string.
     *
     * @param cam The Mars Rover Cam to convert.
     * @return A JSON string representation of the Mars Rover Cam.
     */
    @TypeConverter
    fun fromMarsRoverCam(cam: MarsRoverCam): String {
        val adapter = moshi.adapter(MarsRoverCam::class.java)
        return adapter.toJson(cam)
    }

    /**
     * Converts a JSON string to a Mars Rover Cam.
     *
     * @param json The JSON string to convert.
     * @return A Mars Rover Cam object.
     */
    @TypeConverter
    fun toMarsRoverCam(json: String): MarsRoverCam {
        val adapter = moshi.adapter(MarsRoverCam::class.java)
        return adapter.fromJson(json)!!
    }
}

@Parcelize
@Entity(tableName = "mars_rover_photos")
@TypeConverters(MarsRoverPhotoConverters::class)
data class MarsRoverPhoto(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @Json(name = "sol") val sol: Int,
    @Json(name = "camera") val camera: MarsRoverCam,
    @Json(name = "img_src") val imgSrcUrl: String,
    @Json(name = "earth_date") val earthDate: String,
    @Json(name = "rover") val rover: MarsRover
) : Parcelable