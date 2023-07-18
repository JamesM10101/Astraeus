package com.jamesm10101.astraeus.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.apis.PlanetaryAPI
import com.jamesm10101.astraeus.data.APOD
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _apodResult = MutableLiveData<APOD>()

    // accessor
    val apodResult: LiveData<APOD> = _apodResult

    init {
        getCurrentAPOD(_apodResult)
    }

    /**
     * Gets the latest apod from NASA's APOD API
     *
     * @param apodResult A mutable live data object that stores the apod
     */
    private fun getCurrentAPOD(
        apodResult: MutableLiveData<APOD>
    ) {
        viewModelScope.launch {
            try {
                apodResult.value = PlanetaryAPI.retrofitService.getCurrentApod()
            } catch (e: Exception) {
                Log.e("GetCurrentAPOD", e.message.toString())
            }
        }
    }
}