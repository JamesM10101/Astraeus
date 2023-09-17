package com.jamesm10101.astraeus.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.databases.SavedIVLImageDao
import com.jamesm10101.astraeus.state.SavedIVLImageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SavedIVLSearchResultViewModel(
    private val savedIVLImageDao: SavedIVLImageDao
) : ViewModel() {

    private val _state = MutableStateFlow(SavedIVLImageState())

    private val _ivlResults = savedIVLImageDao.getAllIVLOrderByIdDesc()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(
        _state, _ivlResults
    ) { state, ivlResults ->
        state.copy(
            ivl = ivlResults
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SavedIVLImageState())

}

class SavedIVLSearchResultViewModelFactory(
    private val savedIVLImageDao: SavedIVLImageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedIVLSearchResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return SavedIVLSearchResultViewModel(savedIVLImageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}