package com.jamesm10101.astraeus.viewModels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.ApodSortType
import com.jamesm10101.astraeus.databases.SavedApodDao
import com.jamesm10101.astraeus.state.SavedApodState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@ExperimentalCoroutinesApi
class SavedApodsViewModel(
    private val savedApodDao: SavedApodDao
) : ViewModel() {
    private val _state = MutableStateFlow(SavedApodState())
    private val _sortType = MutableStateFlow(ApodSortType.ID)

    // posts
    private val _apods = _sortType.flatMapLatest { sortType ->
        when (sortType) {
            ApodSortType.ID -> {
                savedApodDao.getAllApodOrderByIdDesc()
            }

            ApodSortType.DATE -> {
                savedApodDao.getAllApodOrderByDateDesc()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(
        _state, _apods, _sortType
    ) { state, apods, sortType ->
        state.copy(
            apods = apods, apodSortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavedApodState())

    /**
     * Alternates between the apod sort types.
     *
     * @return the click listener
     * @see [com.jamesm10101.astraeus.data.ApodSortType]
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
            ApodSortType.ID -> ApodSortType.DATE
            ApodSortType.DATE -> ApodSortType.ID
        }
    }
}

class SavedApodsViewModelFactory(
    private val savedApodDao: SavedApodDao
) : ViewModelProvider.Factory {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedApodsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedApodsViewModel(savedApodDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}