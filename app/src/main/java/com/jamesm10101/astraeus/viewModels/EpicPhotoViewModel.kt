package com.jamesm10101.astraeus.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.databases.SavedEpicDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EpicPhotoViewModel(
    val epic: Epic, private val epicDao: SavedEpicDao
) : ViewModel() {

    private val _showEpicDetails = MutableLiveData<Boolean>()
    val showEpicDetails: LiveData<Boolean> = _showEpicDetails

    val epicSaved = epicDao.getSavedEpicFromId(epic.id)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    /**
     * Saves an Epic if it does not exist, deletes if it does.
     */
    fun handleBookmarkClick(): View.OnClickListener {
        return View.OnClickListener {
            viewModelScope.launch {
                when (epicSaved.value != null) {
                    true -> epicDao.deleteEpic(epicSaved.value!!)
                    false -> epicDao.saveEpic(epic)
                }
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

class EpicPhotoViewModelFactory(
    private val epic: Epic, private val savedEpicDao: SavedEpicDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpicPhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return EpicPhotoViewModel(epic, savedEpicDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}