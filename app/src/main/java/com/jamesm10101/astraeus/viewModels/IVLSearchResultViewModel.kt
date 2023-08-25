package com.jamesm10101.astraeus.viewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IVLSearchResultViewModel : ViewModel() {
    private val _showFullDescription = MutableLiveData<Boolean>(false)

    val showFullDetails: LiveData<Boolean> = _showFullDescription

    /**
     * Toggles showFullDescription, used to toggle the max line count.
     *
     */
    private fun toggleShowFullDetails() {
        _showFullDescription.value = _showFullDescription.value != true
    }

    /**
     * Toggles the showFullDetails variable.
     *
     * @return the onClickListener
     */
    fun onDescriptionTextClickListener(): View.OnClickListener {
        return View.OnClickListener {
            toggleShowFullDetails()
        }
    }
}