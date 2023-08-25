package com.jamesm10101.astraeus.data

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.jamesm10101.astraeus.R

class ExploreSuggestionItems {

    private val explorePageItems = mapOf(
        "Andromeda" to mapOf(
            "thumb" to R.drawable.thumb_andromeda,
            "type" to ExploreSuggestionEnums.SEARCH
        ),
        "Perseverance" to mapOf(
            "thumb" to R.drawable.thumb_rover_perseverance,
            "type" to ExploreSuggestionEnums.ROVER
        ),
        "APOD" to mapOf(
            "thumb" to R.drawable.thumb_apod_logo,
            "type" to ExploreSuggestionEnums.APOD
        ),
        "Juno" to mapOf(
            "thumb" to R.drawable.thumb_juno,
            "type" to ExploreSuggestionEnums.SEARCH
        ),
        "Opportunity" to mapOf(
            "thumb" to R.drawable.thumb_rover_opportunity,
            "type" to ExploreSuggestionEnums.ROVER
        ),
        "Black Holes" to mapOf(
            "thumb" to R.drawable.thumb_black_hole,
            "type" to ExploreSuggestionEnums.SEARCH
        ),
        "Milky Way" to mapOf(
            "thumb" to R.drawable.thumb_milky_way,
            "type" to ExploreSuggestionEnums.SEARCH
        ),
        "Curiosity" to mapOf(
            "thumb" to R.drawable.thumb_rover_curiosity,
            "type" to ExploreSuggestionEnums.ROVER
        ),
        "Spirit" to mapOf(
            "thumb" to R.drawable.thumb_rover_spirit,
            "type" to ExploreSuggestionEnums.ROVER
        )
    )

    fun getItems(context: Context): List<ExploreSuggestionItem> {

        val exploreItems: ArrayList<ExploreSuggestionItem> = ArrayList()

        for (key in explorePageItems.keys) {
            exploreItems.add(
                ExploreSuggestionItem(
                    key,
                    AppCompatResources.getDrawable(
                        context,
                        explorePageItems[key]?.get("thumb") as Int
                    )!!,
                    explorePageItems[key]?.get("type") as ExploreSuggestionEnums
                )
            )
        }

        return exploreItems
    }
}