package com.jamesm10101.astraeus.data

/**
 * Enumeration representing the status of an image download operation.
 */
enum class DownloadImageStatus {
    SUCCESS, EXCEPTION
}

/**
 * Data class representing the response of an image download operation.
 *
 * @property status The [DownloadImageStatus] indicating the result of the download operation.
 * @property message A message providing additional information about the download operation.
 */
data class DownloadImageResponse(
    val status: DownloadImageStatus,
    val message: String
)

