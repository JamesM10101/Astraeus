package com.jamesm10101.astraeus.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EpicPhotoViewModel : ViewModel() {

    private val _showEpicDetails = MutableLiveData<Boolean>()
    private val _showTouchImage = MutableLiveData<Boolean>()

    // accessors
    val showEpicDetails: LiveData<Boolean> = _showEpicDetails
    val showTouchImage: LiveData<Boolean> = _showTouchImage

    /**
     * Toggles the visibility of the TouchImage component
     *
     * @return the click listener for the view
     */
    fun toggleShowTouchImage(): View.OnClickListener {
        return View.OnClickListener {
            try {
                _showTouchImage.value = _showTouchImage.value != true
            } catch (e: Exception) {
                Log.e("showEpicTouchImage", e.message.toString())
            }
        }
    }

    /**
     * Toggles the visibility of the image details component
     *
     * @return the click listener for the view
     */
    fun toggleShowImageDetails(): View.OnClickListener {
        return View.OnClickListener {
            try {
                _showEpicDetails.value = _showEpicDetails.value != true
            } catch (e: Exception) {
                Log.e("showEpicDetails", e.message.toString())
            }
        }
    }
}