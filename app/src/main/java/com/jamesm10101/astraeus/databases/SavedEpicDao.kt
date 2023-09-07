package com.jamesm10101.astraeus.databases

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jamesm10101.astraeus.data.Epic
import kotlinx.coroutines.flow.Flow

/**
 * This class provides DAO methods for saved EPICs.
 */
@Dao
interface SavedEpicDao {
    /**
     * Saves an EPIC.
     *
     * @param epic The EPIC to save.
     */
    @Upsert
    suspend fun saveEpic(epic: Epic)

    /**
     * Deletes an EPIC.
     *
     * @param epic The EPIC to delete.
     */
    @Delete
    suspend fun deleteEpic(epic: Epic)

    /**
     * Gets all saved EPICs ordered by ID.
     *
     * @return All saved EPICs ordered by ID.
     */
    @Query("SELECT * FROM epics ORDER BY id ASC")
    fun getAllEpicOrderById(): Flow<List<Epic>>

    /**
     * Gets all saved EPICs ordered by date.
     *
     * @return All saved EPICs ordered by date.
     */
    @Query("SELECT * FROM epics ORDER BY date ASC")
    fun getAllEpicOrderByDate(): Flow<List<Epic>>
}
