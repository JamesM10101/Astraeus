package com.jamesm10101.astraeus.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.apis.MarsRoverAPI
import com.jamesm10101.astraeus.data.MarsRover
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

class MarsRoverExploreViewModel : ViewModel() {

    private lateinit var roverName: String
    private var loads: Int = 0

    private val _roverDetails = MutableLiveData<MarsRover>()
    private val _roverPhotos = MutableLiveData<List<MarsRoverPhoto>>()

    // accessors
    val roverDetails: LiveData<MarsRover> = _roverDetails
    val roverPhotos: LiveData<List<MarsRoverPhoto>> = _roverPhotos

    /**
     * Initializes the view model by setting the rover name
     * and calling the necessary initialization functions
     *
     * @param name the name of the rover being viewed
     */
    fun initialize(name: String) {
        roverName = name
        getMarsRoverDetails()
        loadMarsRoverImages(null)
    }

    /**
     * Calls getRoverPhotosSol and adds the contents to the apod image list
     *
     * @see [com.jamesm10101.astraeus.apis.MarsRoverAPIService.getRoverPhotosSol]
     *
     * @param callback a callback function that executes when the api call
     * and all related executions have finished
     */
    fun loadMarsRoverImages(callback: (() -> Unit)?) {
        viewModelScope.launch {
            try {
                // load in photos
                val photoList = MarsRoverAPI.retrofitService.getRoverPhotosSol(
                    roverName, sol = _roverDetails.value!!.maxSol - loads
                ).photos.reversed()

                // add photos to the list
                if (_roverPhotos.value == null) {
                    _roverPhotos.value = photoList
                } else {
                    _roverPhotos.value = _roverPhotos.value?.plus(photoList)
                }

                loads++

                if (callback != null) {
                    callback()
                }
            } catch (e: Exception) {
                Log.d("marsRoverPhotos", e.message.toString())
            }
        }
    }

    /**
     * Calls getRoverDetails and sets the contents of rover details
     *
     * @see [com.jamesm10101.astraeus.apis.MarsRoverAPIService.getRoverDetails]
     */
    private fun getMarsRoverDetails() {
        viewModelScope.launch {
            try {
                _roverDetails.value = MarsRoverAPI.retrofitService.getRoverDetails(roverName).rover
            } catch (e: Exception) {
                Log.d("marsRoverDetails", e.message.toString())
            }
        }
    }

}