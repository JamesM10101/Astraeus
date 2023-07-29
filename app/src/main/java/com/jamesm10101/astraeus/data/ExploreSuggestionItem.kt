package com.jamesm10101.astraeus.data

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ExploreSuggestionItem(
    val name: String,
    val drawableRes: @RawValue Drawable,
    val type: ExploreSuggestionEnums
) : Parcelable
