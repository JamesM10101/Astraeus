package com.jamesm10101.astraeus.views

import android.app.Application
import com.jamesm10101.astraeus.databases.SavedPostsDatabase

class AstraeusApplication : Application() {
    val savedPostsDatabase: SavedPostsDatabase by lazy { SavedPostsDatabase.getDatabase(this) }
}