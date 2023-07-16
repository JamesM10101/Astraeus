package com.jamesm10101.astraeus.utils

import com.jamesm10101.astraeus.data.EpicCollection
import com.jamesm10101.astraeus.data.EpicImageType

fun epicImageConverter(image: String, collection: EpicCollection, imgType: EpicImageType): String {

    lateinit var year: String
    lateinit var month: String
    lateinit var day: String

    val imgExtension: String = when (imgType) {
        EpicImageType.PNG -> "png"
        EpicImageType.JPG -> "jpg"
        EpicImageType.THUMBS -> "jpg"
    }

    when (collection) {
        EpicCollection.NATURAL -> {
            year = image.substring(8, 12)
            month = image.substring(12, 14)
            day = image.substring(14, 16)
        }

        EpicCollection.ENHANCED -> {
            year = image.substring(9, 13)
            month = image.substring(13, 15)
            day = image.substring(15, 17)
        }

        EpicCollection.AEROSOL -> {
            year = image.substring(10, 14)
            month = image.substring(14, 16)
            day = image.substring(16, 18)
        }

        EpicCollection.CLOUD -> {
            year = image.substring(19, 23)
            month = image.substring(23, 25)
            day = image.substring(25, 27)
        }

        else -> {}
    }

    return "https://epic.gsfc.nasa.gov/archive/${collection.name.lowercase()}/${year}/${month}/${day}/${imgExtension.lowercase()}/${image}.${imgExtension}"
}