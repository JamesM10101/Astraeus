package com.jamesm10101.astraeus.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jamesm10101.astraeus.data.NasaIVLImageData
import kotlinx.coroutines.flow.Flow

/**
 * This class provides DAO methods for saved IVL images.
 */
@Dao
interface SavedIVLImageDao {

    /**
     * Saves an IVL image.
     *
     * @param nasaIVLImage The IVL image to save.
     */
    @Upsert
    suspend fun saveIVLImage(nasaIVLImage: NasaIVLImageData)

    /**
     * Deletes an IVL image.
     *
     * @param nasaIVLImage The IVL image to delete.
     */
    @Delete
    suspend fun deleteIVLImage(nasaIVLImage: NasaIVLImageData)

    /**
     * Gets all saved IVL images ordered by ID.
     *
     * @return All saved IVL images ordered by ID.
     */
    @Query("SELECT * FROM ivl_images ORDER BY nasaId ASC")
    fun getAllIVLOrderById(): Flow<List<NasaIVLImageData>>

    /**
     * Gets all saved IVL images containing a specific keyword ordered by ID.
     *
     * @return All saved IVL images containing a specific keyword ordered by ID.
     */
    @Query("SELECT * FROM ivl_images WHERE keywords LIKE :keyword ORDER BY nasaId ASC")
    fun getAllIVLByKeywordOrderById(keyword: String): Flow<List<NasaIVLImageData>>

}
