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
     * Gets a saved APOD with a matching date if one exists.
     *
     * @param apodDate The date of the apod to check for.
     * @return The saved APOD if one exists, null otherwise.
     */
    @Query("SELECT * FROM apods WHERE date = :apodDate")
    fun getSavedApodFromDate(apodDate: String): Flow<APOD?>

    /**
     * Gets all saved APODs ordered by ID.
     *
     * @return All saved APODs ordered by ID.
     */
    @Query("SELECT * FROM apods ORDER BY id ASC")
    fun getAllApodOrderByIdAsc(): Flow<List<APOD>>

    /**
     * Gets all saved APODs ordered by ID.
     *
     * @return All saved APODs ordered by ID.
     */
    @Query("SELECT * FROM apods ORDER BY id DESC")
    fun getAllApodOrderByIdDesc(): Flow<List<APOD>>

    /**
     * Gets all saved APODs ordered by date.
     *
     * @return All saved APODs ordered by date.
     */
    @Query("SELECT * FROM apods ORDER BY date ASC")
    fun getAllApodOrderByDateAsc(): Flow<List<APOD>>

    /**
     * Gets all saved APODs ordered by date.
     *
     * @return All saved APODs ordered by date.
     */
    @Query("SELECT * FROM apods ORDER BY date DESC")
    fun getAllApodOrderByDateDesc(): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by ID.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by ID.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY id ASC")
    fun getAllApodByMediaTypeAndOrderByIdAsc(mediaType: String): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by ID.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by ID.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY id DESC")
    fun getAllApodByMediaTypeAndOrderByIdDesc(mediaType: String): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by date.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by date.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY date ASC")
    fun getAllApodByMediaTypeAndOrderByDateAsc(mediaType: String): Flow<List<APOD>>

    /**
     * Gets all saved APODs of a specific media type ordered by date.
     *
     * @param mediaType The media type of the APODs to get.
     * @return All saved APODs of the specified media type ordered by date.
     */
    @Query("SELECT * FROM apods WHERE mediaType = :mediaType ORDER BY date DESC")
    fun getAllApodByMediaTypeAndOrderByDateDesc(mediaType: String): Flow<List<APOD>>
}
