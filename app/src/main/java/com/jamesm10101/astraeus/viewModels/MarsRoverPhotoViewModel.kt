package com.jamesm10101.astraeus.viewModels

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databases.SavedMarsRoverPhotoDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Exception

class MarsRoverPhotoViewModel(
    val marsRoverPhoto: MarsRoverPhoto,
    private val marsRoverPhotoDao: SavedMarsRoverPhotoDao,
    private val resources: Resources,
) : ViewModel() {

    private val _showRoverDetails = MutableLiveData<Boolean>()
    val showRoverDetails: LiveData<Boolean> = _showRoverDetails

    val photoSaved = marsRoverPhotoDao.getSavedPhotoFromId(marsRoverPhoto.id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    /**
     * Saves a MarsRoverPhoto if it does not exist, deletes it if does.
     *
     * @param marsRoverPhoto The MarsRoverPhoto to save/delete.
     */
    fun handleBookmarkClick(marsRoverPhoto: MarsRoverPhoto) {
        viewModelScope.launch {
            when (photoSaved.value != null) {
                true -> marsRoverPhotoDao.deleteMarsRoverPhoto(photoSaved.value!!)
                false -> marsRoverPhotoDao.saveMarsRoverPhoto(marsRoverPhoto)
            }
        }
    }

    /**
     * Creates an onClickListener that exposes the rovers details
     *
     * @return the click listener for the view
     */
    fun toggleShowRoverDetails(): View.OnClickListener {

        return View.OnClickListener {
            try {
                val txtV = it as TextView

                // update the arrow drawable
                val drawable: Drawable? = ResourcesCompat.getDrawable(
                    resources, when (_showRoverDetails.value == true) {
                        true -> R.drawable.ic_arrow_head_down
                        false -> R.drawable.ic_arrow_head_up
                    }, null
                )

                txtV.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

                // toggle the details visibility
                _showRoverDetails.value = _showRoverDetails.value != true
            } catch (e: Exception) {
                Log.e("showRoverDetails", e.message.toString())
            }
        }
    }

}

class MarsRoverPhotoViewModelFactory(
    private val marsRoverPhoto: MarsRoverPhoto,
    private val marsRoverPhotoDao: SavedMarsRoverPhotoDao,
    private val resources: Resources
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarsRoverPhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MarsRoverPhotoViewModel(
                marsRoverPhoto, marsRoverPhotoDao, resources
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}