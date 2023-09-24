package com.jamesm10101.astraeus.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExploreSuggestionItem(
    val name: String,
    val imgUrl: String,
    val type: ExploreSuggestionEnums
) : Parcelable
