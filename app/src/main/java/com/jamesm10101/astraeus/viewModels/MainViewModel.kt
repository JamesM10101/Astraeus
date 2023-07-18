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
                val res = PlanetaryAPI.retrofitService.getCurrentApod()

                // fix copyright formatting
                val copyright = res.copyright?.replace("\n", "")

                apodResult.value = APOD(
                    imgSrcUrl = res.imgSrcUrl,
                    imgSrcHDUrl = res.imgSrcHDUrl,
                    copyright = copyright,
                    date = res.date,
                    mediaType = res.mediaType,
                    title = res.title,
                    serviceVersion = res.serviceVersion,
                    explanation = res.explanation
                )

            } catch (e: Exception) {
                Log.e("GetCurrentAPOD", e.message.toString())
            }
        }
    }
}