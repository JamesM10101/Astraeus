package com.jamesm10101.astraeus.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * This class provides type converters for Room database.
 *
 * @see [com.jamesm10101.astraeus.data.Epic]
 */
class EpicConverters {
    /**
     * Converts coordinates to a string.
     *
     * @param coordinates The coordinates to convert.
     * @return A string representation of the coordinates.
     */
    @TypeConverter
    fun fromCoordinates(coordinates: Coordinates): String {
        return "${coordinates.lat},${coordinates.lon}"
    }

    /**
     * Converts a string to coordinates.
     *
     * @param coordinatesString The string to convert.
     * @return A Coordinates object.
     */
    @TypeConverter
    fun toCoordinates(coordinatesString: String): Coordinates {
        val (lat, lon) = coordinatesString.split(",")
        return Coordinates(lat.toFloat(), lon.toFloat())
    }

    /**
     * Converts satellite position to a string.
     *
     * @param position The satellite position to convert.
     * @return A string representation of the satellite position.
     */
    @TypeConverter
    fun fromSatPosition(position: SatellitePosition): String {
        return "${position.x},${position.y},${position.z}"
    }

    /**
     * Converts a string to satellite position.
     *
     * @param position The string to convert.
     * @return A SatellitePosition object.
     */
    @TypeConverter
    fun toSatPosition(position: String): SatellitePosition {
        val (x, y, z) = position.split(",")
        return SatellitePosition(x.toFloat(), y.toFloat(), z.toFloat())
    }

    /**
     * Converts attitude quaternions to a string.
     *
     * @param quaternions The attitude quaternions to convert.
     * @return A string representation of the attitude quaternions.
     */
    @TypeConverter
    fun fromAttQuaternions(quaternions: AttitudeQuaternions): String {
        return "${quaternions.q0},${quaternions.q1},${quaternions.q2},${quaternions.q3}"
    }

    /**
     * Converts a string to attitude quaternions.
     *
     * @param quaternions The string to convert.
     * @return An AttitudeQuaternions object.
     */
    @TypeConverter
    fun toAttQuaternions(quaternions: String): AttitudeQuaternions {
        val (q0, q1, q2, q3) = quaternions.split(",")
        return AttitudeQuaternions(q0.toFloat(), q1.toFloat(), q2.toFloat(), q3.toFloat())
    }
}


@Parcelize
@Entity(tableName = "epics")
@TypeConverters(EpicConverters::class)
data class Epic(
    @PrimaryKey @Json(name = "identifier") val id: String,
    @Json(name = "caption") val caption: String,
    @Json(name = "image") val image: String,
    var imgSrcUrl: String?,
    @Json(name = "version") val version: String,
    @Json(name = "date") val date: String,
    @Json(name = "centroid_coordinates") val centroidCoords: Coordinates,
    @Json(name = "dscovr_j2000_position") val dscovrPos: SatellitePosition,
    @Json(name = "lunar_j2000_position") val lunarPos: SatellitePosition,
    @Json(name = "sun_j2000_position") val sunPos: SatellitePosition,
    @Json(name = "attitude_quaternions") val attQuaternions: AttitudeQuaternions,
) : Parcelable