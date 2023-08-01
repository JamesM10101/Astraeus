package com.jamesm10101.astraeus.utils

/**
 * Gets the youtube embed video id
 *
 * @param mediaSrc the link to the Apods default media source
 * @return The youtube embeds video id if available and an empty string otherwise
 */
fun getApodUrlEmbed(mediaSrc: String): String {

    if (mediaSrc.contains("https://www.youtube.com/embed")) {
        val qMarkIdx = mediaSrc.indexOf('?')

        return if (qMarkIdx == -1) {
            mediaSrc.substring(30)
        } else {
            mediaSrc.substring(30, qMarkIdx)
        }
    }

    // unknown format
    return ""
}