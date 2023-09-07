package com.jamesm10101.astraeus.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.data.NasaIVLImageData
import kotlin.concurrent.Volatile

@Database(
    entities = [APOD::class, Epic::class, MarsRoverPhoto::class, NasaIVLImageData::class],
    version = 1
)
abstract class SavedPostsDatabase : RoomDatabase() {

    abstract val savedApodDao: SavedApodDao
    abstract val savedEpicDao: SavedEpicDao
    abstract val savedIVLImageDao: SavedIVLImageDao
    abstract val savedMarsRoverPhotoDao: SavedMarsRoverPhotoDao

    companion object {
        @Volatile
        private var INSTANCE: SavedPostsDatabase? = null

        fun getDatabase(context: Context): SavedPostsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SavedPostsDatabase::class.java,
                    "saved_posts"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}