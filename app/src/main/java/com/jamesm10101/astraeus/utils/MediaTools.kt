package com.jamesm10101.astraeus.utils

import java.net.URLEncoder
import java.net.URLConnection

/**
 * Provides utility functions for working with media files.
 */
class MediaTools {

    companion object {

        /**
         * Returns the MIME type of a file based on its name.
         *
         * @param fileName The name of the file.
         * @return The MIME type of the file.
         */
        fun getMimeType(fileName: String): String {
            val encoded: String = try {
                URLEncoder.encode(fileName, "UTF-8").replace("+", "%20")
            } catch (e: Exception) {
                fileName
            }

            return URLConnection.getFileNameMap().getContentTypeFor(encoded).lowercase()
        }
    }
}