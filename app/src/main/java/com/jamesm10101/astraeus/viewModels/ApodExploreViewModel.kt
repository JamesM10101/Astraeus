package com.jamesm10101.astraeus.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.apis.PlanetaryAPI
import com.jamesm10101.astraeus.data.APOD
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ApodExploreViewModel : ViewModel() {
    private val _apodImages = MutableLiveData<List<APOD>>()

    // accessors
    val apodImages: LiveData<List<APOD>> = _apodImages

    private val oneDay = 86400000L
    private val loadCount = 14

    private var endDate = Date()
    private var startDate = Date(endDate.time - oneDay * loadCount)

    init {
        loadApodImages(null)
    }

    fun loadApodImages(callback: (() -> Unit)?) {
        viewModelScope.launch {
            try {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                val dateSubtraction =
                    when (_apodImages.value != null) { // used to stop loading duplicates
                        true -> oneDay

                        false -> 0L
                    }

                // load in images
                val images = PlanetaryAPI.retrofitService.getApodByDateRange(
                    dateFormatter.format(startDate),
                    dateFormatter.format(Date(endDate.time - dateSubtraction))
                )

                // add the images to the recyclers list
                if (_apodImages.value == null) {
                    _apodImages.value = images.reversed()
                } else {
                    _apodImages.value = _apodImages.value?.plus(images.reversed())
                }

                // calculate the start and end dates
                startDate = Date(startDate.time - oneDay * loadCount)
                endDate = Date(endDate.time - oneDay * loadCount)

                // execute any callback
                if (callback != null) {
                    callback()
                }

            } catch (e: Exception) {
                Log.d("apodExploreGetImages", e.message.toString())
            }
        }
    }
}