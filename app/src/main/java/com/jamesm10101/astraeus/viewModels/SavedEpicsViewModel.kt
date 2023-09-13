package com.jamesm10101.astraeus.viewModels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.data.EpicSortType
import com.jamesm10101.astraeus.databases.SavedApodDao
import com.jamesm10101.astraeus.databases.SavedEpicDao
import com.jamesm10101.astraeus.state.SavedEpicState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@ExperimentalCoroutinesApi
class SavedEpicsViewModel(
    private val savedEpicDao: SavedEpicDao
) : ViewModel() {
    private val _state = MutableStateFlow(SavedEpicState())
    private val _sortType = MutableStateFlow(EpicSortType.ID)

    // posts
    private val _epics = _sortType.flatMapLatest { sortType ->
        when (sortType) {
            EpicSortType.ID -> {
                savedEpicDao.getAllEpicOrderByIdDesc()
            }

            EpicSortType.DATE -> {
                savedEpicDao.getAllEpicOrderByDateDesc()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(
        _state, _epics, _sortType
    ) { state, epics, sortType ->
        state.copy(
            epics = epics, epicSortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavedEpicState())

    fun sortClick(): View.OnClickListener {
        return View.OnClickListener {
            toggleSort()
        }
    }

    private fun toggleSort() {
        _sortType.value = when (_sortType.value) {
            EpicSortType.ID -> EpicSortType.DATE
            EpicSortType.DATE -> EpicSortType.ID
        }
    }
}

class SavedEpicsViewModelFactory(
    private val savedEpicDao: SavedEpicDao
) : ViewModelProvider.Factory {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedEpicsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedEpicsViewModel(savedEpicDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}