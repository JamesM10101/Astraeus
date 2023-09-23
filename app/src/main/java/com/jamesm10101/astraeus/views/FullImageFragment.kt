package com.jamesm10101.astraeus.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.jamesm10101.astraeus.data.DownloadImageResponse
import com.jamesm10101.astraeus.data.DownloadImageStatus
import com.jamesm10101.astraeus.utils.MediaDownload
import com.jamesm10101.astraeus.utils.AlertUtils
import com.jamesm10101.astraeus.viewModels.MainViewModel
import kotlinx.coroutines.launch

open class FullImageFragment : Fragment() {

    private lateinit var imageUrl: String

    private val requestStoragePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                handleImageDownload()
            } else {
                AlertUtils.showStoragePermissionAlertDialog(requireContext())
            }
        }

    /**
     * Checks for the necessary storage permissions and either proceeds
     * with the image download or displays a permission dialog.
     *
     * @param url The URL of the image to download.
     */
    fun handleUserRequestedImageDownload(url: String) {
        imageUrl = url

        val permReq: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        }

        when {
            ContextCompat.checkSelfPermission(
                requireContext(), permReq
            ) == PackageManager.PERMISSION_GRANTED -> {
                handleImageDownload()
            }

            shouldShowRequestPermissionRationale(
                permReq
            ) -> {
                AlertUtils.showStoragePermissionAlertDialog(requireContext())
            }

            else -> {
                requestStoragePermissionLauncher.launch(permReq)
            }
        }
    }

    /**
     * Handles the image download operation and displays the appropriate alert dialogs.
     */
    private fun handleImageDownload() {
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            val downloadStatus: DownloadImageResponse? =
                when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    true -> MediaDownload.downloadImage(requireContext(), imageUrl)
                    false -> null // TODO -- Write download image function for api levels 24 - 28
                }

            when (downloadStatus?.status) {
                DownloadImageStatus.SUCCESS -> {
                    AlertUtils.showBasicSuccessAlertDialog(
                        requireContext(),
                        "Image Downloaded",
                        null
                    )
                }

                DownloadImageStatus.EXCEPTION -> {
                    AlertUtils.showBasicErrorAlertDialog(
                        requireContext(),
                        "Error Downloading Image",
                        downloadStatus.message
                    )
                }

                else -> {
                    AlertUtils.showBasicErrorAlertDialog(
                        requireContext(),
                        "Error Downloading Image",
                        "Unknown Error Occurred"
                    )
                }
            }
        }
    }

    /**
     * Interacts with the viewModel to hide an active TouchImageFull or pop the backstack
     *
     * @param viewModel the MainViewModel to interact with
     */
    fun handleBackPress(viewModel: MainViewModel) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (viewModel.showTouchImage.value == true) {
                viewModel.toggleShowTouchImage()
            } else {
                parentFragmentManager.popBackStack()
            }
        }
    }
}