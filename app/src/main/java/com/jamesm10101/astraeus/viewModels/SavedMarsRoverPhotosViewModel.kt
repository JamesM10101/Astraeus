@file:OptIn(ExperimentalCoroutinesApi::class)

package com.jamesm10101.astraeus.viewModels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.MarsRoverPhotoSortType
import com.jamesm10101.astraeus.databases.SavedMarsRoverPhotoDao
import com.jamesm10101.astraeus.state.SavedRoverPhotosState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class SavedMarsRoverPhotosViewModel(
    private val savedMarsRoverPhotoDao: SavedMarsRoverPhotoDao,
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRoverPhotosState())
    private val _sortType = MutableStateFlow(MarsRoverPhotoSortType.ID)

    private val _photos = _sortType.flatMapLatest { sortType ->
        when (sortType) {
            MarsRoverPhotoSortType.ID -> {
                savedMarsRoverPhotoDao.getAllMarsRoverPhotosOrderByIdDesc()
            }

            MarsRoverPhotoSortType.SOL -> {
                savedMarsRoverPhotoDao.getAllMarsRoverPhotosOrderBySolDesc()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(
        _state, _photos, _sortType
    ) { state, photos, sortType ->
        state.copy(
            roverPhotos = photos, marsRoverPhotoSortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavedRoverPhotosState())

    /**
     * Alternates between the MarsRoverPhoto sort types.
     *
     * @return The click listener
     * @see [com.jamesm10101.astraeus.data.MarsRoverPhotoSortType]
     */
    fun sortClick(): View.OnClickListener {
        return View.OnClickListener {
            toggleSort()
        }
    }

    /**
     * Alternates between the apod sort types.
     *
     * @see [com.jamesm10101.astraeus.data.ApodSortType]
     */
    private fun toggleSort() {
        _sortType.value = when (_sortType.value) {
            MarsRoverPhotoSortType.ID -> MarsRoverPhotoSortType.SOL
            MarsRoverPhotoSortType.SOL -> MarsRoverPhotoSortType.ID
        }
    }
}

class SavedMarsRoverPhotosViewModelFactory(
    private val savedMarsRoverPhotoDao: SavedMarsRoverPhotoDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedMarsRoverPhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedMarsRoverPhotosViewModel(savedMarsRoverPhotoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}