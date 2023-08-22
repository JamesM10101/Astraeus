package com.jamesm10101.astraeus.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsRoverInstrument(
    val name: String,
    val description: String,
    val imgUrl: String,
) : Parcelable
