package com.jamesm10101.astraeus.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.apis.EpicAPI
import com.jamesm10101.astraeus.apis.MarsRoverAPI
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.EpicCollection
import com.jamesm10101.astraeus.data.EpicImageType
import com.jamesm10101.astraeus.data.MarsRoverLatestPhotos
import com.jamesm10101.astraeus.data.MarsRoverName
import com.jamesm10101.astraeus.utils.epicImageConverter
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val _epicLatest = MutableLiveData<List<Epic>>()
    private val _rover1Name = MutableLiveData<String>()
    private val _rover2Name = MutableLiveData<String>()
    private val _rover1photos = MutableLiveData<MarsRoverLatestPhotos>()
    private val _rover2photos = MutableLiveData<MarsRoverLatestPhotos>()

    // accessors
    val epicLatest: LiveData<List<Epic>> = _epicLatest
    val rover1Name: LiveData<String> = _rover1Name
    val rover2Name: LiveData<String> = _rover2Name
    val rover1photos: LiveData<MarsRoverLatestPhotos> = _rover1photos
    val rover2photos: LiveData<MarsRoverLatestPhotos> = _rover2photos

    init {
        getEpicLatest(_epicLatest, EpicCollection.ENHANCED, EpicImageType.JPG)
        getRovers()
    }

    /**
     * Sets the rover name and photos variables.
     *
     */
    private fun getRovers() {
        _rover1Name.value = MarsRoverName.PERSEVERANCE.name.lowercase().replaceFirstChar {
            it.uppercase()
        }
        getRoverLatest(MarsRoverName.PERSEVERANCE, _rover1photos)

        _rover2Name.value = MarsRoverName.CURIOSITY.name.lowercase().replaceFirstChar {
            it.uppercase()
        }
        getRoverLatest(MarsRoverName.CURIOSITY, _rover2photos)
    }

    /**
     * Grabs the latest photos form NASA's EPIC api and formats the urls.
     *
     * @param epicList A mutable live data object that stores the list of images
     * @param collType The type of collection to retrieve from EPIC
     * @param imgType The type of image to retrieve from EPIC
     */
    private fun getEpicLatest(
        epicList: MutableLiveData<List<Epic>>,
        collType: EpicCollection,
        imgType: EpicImageType
    ) {
        viewModelScope.launch {
            try {
                epicList.value =
                    EpicAPI.retrofitService.getLatest(collType.name.lowercase())

                // format the urls
                epicList.value!!.forEach {
                    it.imgSrcUrl =
                        epicImageConverter(it.image, collType, imgType)
                }

            } catch (e: java.lang.Exception) {
                Log.e("GetEpicLatest", e.message.toString())
            }
        }
    }

    /**
     * Gets the latest info and images about the specified Mars rover from NASA's Mars Rover API.
     *
     * @param roverName The name of the Mars rover
     * @param roverName A mutable live data object that stores the rover name
     * @param roverPhotos A mutable live data object that stores the latest photos form the rover
     */
    private fun getRoverLatest(
        roverName: MarsRoverName,
        roverPhotos: MutableLiveData<MarsRoverLatestPhotos>
    ) {
        viewModelScope.launch {
            try {
                roverPhotos.value =
                    MarsRoverAPI.retrofitService.getRoverLatestPhotos(roverName.name)
            } catch (e: Exception) {
                Log.e("GetRoverLatest", e.message.toString())
            }
        }
    }
}