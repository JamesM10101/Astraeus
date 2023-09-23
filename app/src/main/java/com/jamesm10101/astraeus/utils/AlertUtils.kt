package com.jamesm10101.astraeus.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jamesm10101.astraeus.R

/**
 * Utility class for handling storage permission-related dialogs.
 */
class AlertUtils {
    companion object {

        /**
         * Shows an alert dialog to request storage permissions from the user.
         *
         * @param context The [Context] in which the dialog should be displayed.
         */
        fun showStoragePermissionAlertDialog(context: Context) {
            MaterialAlertDialogBuilder(context).setTitle(context.getString(R.string.storage_permissions_title))
                .setMessage(context.getString(R.string.download_permissions_message))
                .setPositiveButton(context.getString(R.string.grant)) { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context.packageName, null)
                    intent.data = uri
                    context.startActivity(intent)
                }.setNeutralButton(context.getString(R.string.cancel)) { _, _ -> }
                .setCancelable(false).show()
        }

        /**
         * Shows an error alert dialog related to storage permissions.
         *
         * @param context The [Context] in which the dialog should be displayed.
         * @param title The title to be displayed in the error dialog.
         * @param message The error message to be displayed in the error dialog.
         */
        fun showBasicErrorAlertDialog(
            context: Context, title: String, message: String
        ) {
            MaterialAlertDialogBuilder(context).setIcon(R.drawable.ic_error_outline_filled)
                .setTitle(title).setMessage(message).setPositiveButton(R.string.ok) { _, _ -> }
                .setCancelable(true).show()
        }

        /**
         * Shows a basic success alert dialog used to inform the user about successful actions or events.
         *
         * @param context The [Context] in which the dialog should be displayed.
         * @param title The title to be displayed in the success dialog.
         * @param message An optional message to be displayed in the success dialog. It can be null.
         */
        fun showBasicSuccessAlertDialog(
            context: Context, title: String, message: String?
        ) {
            MaterialAlertDialogBuilder(context).setIcon(R.drawable.ic_check).setTitle(title)
                .setMessage(message).setPositiveButton(context.getString(R.string.ok)) { _, _ -> }
                .setCancelable(true).show()
        }
    }
}
