package com.jamesm10101.astraeus.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class ApodViewModel : ViewModel() {

    private val _showTouchImage = MutableLiveData<Boolean>()

    // accessors
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
                Log.e("showApodTouchImage", e.message.toString())
            }
        }
    }
}