package com.jamesm10101.astraeus.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EpicPhotoViewModel : ViewModel() {

    private val _showEpicDetails = MutableLiveData<Boolean>()

    // accessors
    val showEpicDetails: LiveData<Boolean> = _showEpicDetails

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