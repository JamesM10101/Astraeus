package com.jamesm10101.astraeus.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import kotlinx.coroutines.flow.Flow

/**
 * This class provides DAO methods for saved Mars Rover Photos.
 */
@Dao
interface SavedMarsRoverPhotoDao {
    /**
     * Saves a Mars Rover Photo.
     *
     * @param marsRoverPhoto The Mars Rover Photo to save.
     */
    @Upsert
    suspend fun saveMarsRoverPhoto(marsRoverPhoto: MarsRoverPhoto)

    /**
     * Deletes a Mars Rover Photo.
     *
     * @param marsRoverPhoto The Mars Rover Photo to delete.
     */
    @Delete
    suspend fun deleteMarsRoverPhoto(marsRoverPhoto: MarsRoverPhoto)

    /**
     * Gets all saved Mars rover photos ordered by ID.
     *
     * @return All saved Mars rover photos ordered by ID.
     */
    @Query("SELECT * FROM mars_rover_photos ORDER BY id ASC")
    fun getAllMarsRoverPhotosOrderById(): Flow<List<MarsRoverPhoto>>

    /**
     * Gets all saved Mars rover photos ordered by Sol.
     *
     * @return All saved Mars rover photos ordered by Sol.
     */
    @Query("SELECT * FROM mars_rover_photos ORDER BY sol ASC")
    fun getAllMarsRoverPhotosOrderBySol(): Flow<List<MarsRoverPhoto>>
}