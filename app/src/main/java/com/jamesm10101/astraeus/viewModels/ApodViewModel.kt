package com.jamesm10101.astraeus.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databases.SavedApodDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ApodViewModel(
    val apod: APOD, private val apodDao: SavedApodDao
) : ViewModel() {

    val apodSaved = apodDao.getSavedApodFromDate(apod.date)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    /**
     * Saves an APOD if it does not exist, deletes it if does.
     *
     * @param apod The APOD to save/delete.
     */
    fun handleBookmarkClick(apod: APOD) {
        viewModelScope.launch {
            when (apodSaved.value != null) {
                true -> apodDao.deleteApod(apodSaved.value!!)
                false -> apodDao.saveApod(apod)
            }
        }
    }
}

class ApodViewModelFactory(
    private val apod: APOD,
    private val savedApodDao: SavedApodDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return ApodViewModel(apod, savedApodDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}