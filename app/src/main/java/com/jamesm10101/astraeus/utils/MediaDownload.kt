package com.jamesm10101.astraeus.utils

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.jamesm10101.astraeus.data.DownloadImageResponse
import com.jamesm10101.astraeus.data.DownloadImageStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.net.URL

/**
 * Provides utility functions for downloading media files.
 */
class MediaDownload {

    companion object {

        /**
         * Downloads an image or gif from the given URL and saves it to the device's external storage.
         *
         * @param context The context of the application.
         * @param photoUrl The URL of the image to download.
         * @return A [DownloadImageResponse] object that contains the status of the download operation and a message.
         */
        suspend fun downloadImage(
            context: Context, photoUrl: String
        ): DownloadImageResponse {
            return try {
                withContext(Dispatchers.IO) {
                    val resolver = context.contentResolver
                    val contentValues = ContentValues().apply {
                        put(
                            MediaStore.MediaColumns.DISPLAY_NAME,
                            System.currentTimeMillis().toString()
                        )
                        put(MediaStore.MediaColumns.MIME_TYPE, MediaTools.getMimeType(photoUrl))
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
                    }

                    val imageUri =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    val outputStream: OutputStream? =
                        imageUri?.let { resolver.openOutputStream(it) }

                    if (outputStream == null) {
                        Log.e("downloadImage", "Could not open an output stream.")
                        return@withContext DownloadImageResponse(
                            DownloadImageStatus.EXCEPTION, "Could not open an output stream."
                        )
                    }

                    val inputStream: InputStream = URL(photoUrl).openStream()
                    inputStream.use { input ->
                        outputStream.use { output ->
                            input.copyTo(output)
                        }
                    }
                    outputStream.close()

                    return@withContext DownloadImageResponse(
                        DownloadImageStatus.SUCCESS, "Image Downloaded Successfully"
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return DownloadImageResponse(
                    DownloadImageStatus.EXCEPTION, e.message.toString()
                )
            }
        }
    }
}