package com.jamesm10101.astraeus.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.apis.MarsRoverAPI
import com.jamesm10101.astraeus.data.MarsRover
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

class MarsRoverExploreViewModel : ViewModel() {

    lateinit var roverName: String
    private var loads: Int = 0

    private val _roverDetails = MutableLiveData<MarsRover>()
    private val _roverPhotos = MutableLiveData<List<MarsRoverPhoto>>()
    private val _roverCam = MutableLiveData<String>("")

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
                // load in photos -- use cam if filtered
                val photoList = when (_roverCam.value.isNullOrEmpty()) {
                    true -> {
                        MarsRoverAPI.retrofitService.getRoverPhotosSol(
                            roverName, sol = _roverDetails.value!!.maxSol - loads
                        ).photos.reversed()
                    }

                    false -> {
                        MarsRoverAPI.retrofitService.getRoverPhotosSol(
                            roverName,
                            sol = _roverDetails.value!!.maxSol - loads,
                            camera = _roverCam.value.toString()
                        ).photos.reversed()
                    }

                }

                // add photos to the list
                if (_roverPhotos.value == null) {
                    _roverPhotos.value = photoList
                } else {
                    _roverPhotos.value = _roverPhotos.value?.plus(photoList)
                }

                loads++

                // ensure images are loaded in before callback
                if (photoList.isEmpty()) {
                    loadMarsRoverImages(callback)
                } else if (callback != null) {
                    callback()
                }

            } catch (e: Exception) {
                Log.e("marsRoverPhotos", e.message.toString())
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
                loadMarsRoverImages {}
            } catch (e: Exception) {
                Log.d("marsRoverDetails", e.message.toString())
            }
        }
    }

    /**
     * Filters and loads in new rover images based on the checked chip
     *
     * @return the OnCheckedStateListener
     */
    fun camsCheckedChangedListener(): ChipGroup.OnCheckedStateChangeListener {
        return ChipGroup.OnCheckedStateChangeListener { group, _ ->

            // set the roverCam based on selected chip
            if (group.checkedChipId != -1) {
                val checked = group.findViewById<Chip>(group.checkedChipId)

                if (checked.text.toString() == "All") { // All text will break request
                    _roverCam.value = ""
                } else {
                    _roverCam.value = checked.text.toString()
                }
            } else {
                _roverCam.value = ""
            }

            // reset vars and load in more images
            loads = 0
            _roverPhotos.value = emptyList()
            loadMarsRoverImages { }
        }
    }

    /**
     * Loads in the rover photos when the bottom of the recycler view is reached
     *
     * @param recyclerView the recycler view to listen to
     * @return the OnScrollListener
     */
    fun onRoverExploreScrollListener(recyclerView: RecyclerView): RecyclerView.OnScrollListener {
        val context = recyclerView.context
        val layoutManager =
            GridLayoutManager(context, context.resources.getInteger(R.integer.exploreRecyclerSpan))
        recyclerView.layoutManager = layoutManager

        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    loadMarsRoverImages {
                        // keep scrolled position
                        val state = layoutManager.onSaveInstanceState()
                        layoutManager.onRestoreInstanceState(state)
                    }
                }
            }
        }
    }
}