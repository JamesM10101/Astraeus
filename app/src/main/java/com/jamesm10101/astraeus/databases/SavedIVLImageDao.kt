package com.jamesm10101.astraeus.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jamesm10101.astraeus.data.NasaIVLImage
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
    suspend fun saveIVLImage(nasaIVLImage: NasaIVLImage)

    /**
     * Deletes an IVL image.
     *
     * @param nasaIVLImage The IVL image to delete.
     */
    @Delete
    suspend fun deleteIVLImage(nasaIVLImage: NasaIVLImage)

    /**
     * Gets a saved NasaIVLImageData with a matching id if one exists.
     *
     * @param id The id of the NasaIVLImageData to check for.
     * @return The saved NasaIVLImageData if one exists, null otherwise.
     */
    @Query("SELECT * FROM ivl_images WHERE imgUrl = :imgUrl")
    fun getSavedIvlFromId(imgUrl: String): Flow<NasaIVLImage?>

    /**
     * Gets all saved IVL images ordered by ID.
     *
     * @return All saved IVL images ordered by ID.
     */
    @Query("SELECT * FROM ivl_images ORDER BY id ASC")
    fun getAllIVLOrderByIdAsc(): Flow<List<NasaIVLImage>>

    /**
     * Gets all saved IVL images ordered by ID.
     *
     * @return All saved IVL images ordered by ID.
     */
    @Query("SELECT * FROM ivl_images ORDER BY id DESC")
    fun getAllIVLOrderByIdDesc(): Flow<List<NasaIVLImage>>

}
