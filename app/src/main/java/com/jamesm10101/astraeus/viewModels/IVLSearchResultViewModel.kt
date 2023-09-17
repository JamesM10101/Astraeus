package com.jamesm10101.astraeus.viewModels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.data.NasaIVLImageData
import com.jamesm10101.astraeus.databases.SavedIVLImageDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IVLSearchResultViewModel(
    val ivlImage: NasaIVLImage, private val ivlImageDao: SavedIVLImageDao
) : ViewModel() {

    private val _showFullDescription = MutableLiveData<Boolean>(false)
    val showFullDetails: LiveData<Boolean> = _showFullDescription

    private val ivlImageData: NasaIVLImageData = ivlImage.data[0]
    val ivlSaved = ivlImageDao.getSavedIvlFromId(ivlImage.imgUrl)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    /**
     * Saves the NasaIVLImageData if it does not exist, deletes it if does.
     */
    fun handleBookmarkClick(): View.OnClickListener {
        return View.OnClickListener {
            viewModelScope.launch {
                when (ivlSaved.value != null) {
                    true -> ivlImageDao.deleteIVLImage(ivlSaved.value!!)
                    false -> ivlImageDao.saveIVLImage(ivlImage)
                }
            }
        }
    }

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

class IVLSearchResultViewModelFactory(
    private val ivlImage: NasaIVLImage, private val ivlImageDao: SavedIVLImageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IVLSearchResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return IVLSearchResultViewModel(
                ivlImage, ivlImageDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}