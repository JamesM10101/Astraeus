package com.jamesm10101.astraeus.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jamesm10101.astraeus.data.APOD
import kotlinx.coroutines.flow.Flow

/**
 * This class provides DAO methods for saved APODs.
 */
@Dao
interface SavedApodDao {
    /**
     * Saves an APOD.
     *
     * @param apod The APOD to save.
     */
    @Upsert
    suspend fun saveApod(apod: APOD)

    /**
     * Deletes an APOD.
     *
     * @param apod The APOD to delete.
     */
    @Delete
    suspend fun deleteApod(apod: APOD)

    /**
     * Gets all saved APODs ordered by ID.
     *
     * @return All saved APODs ordered by ID.
     */
    @Query("SELECT * FROM apods ORDER BY id ASC")
    fun getAllApodOrderById(): Flow<List<APOD>>

    /**
     * Gets all saved APODs ordered by date.
     *
     * @return All saved APODs ordered by date.
     */
    @Query("SELECT * FROM apods ORDER BY date ASC")
    fun getAllApodOrderByDate(): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by ID.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by ID.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY id ASC")
    fun getAllApodByMediaTypeAndOrderById(mediaType: String): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by date.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by date.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY date ASC")
    fun getAllApodByMediaTypeAndOrderByDate(mediaType: String): Flow<List<APOD>>
}
