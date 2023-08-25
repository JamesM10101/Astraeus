package com.jamesm10101.astraeus.viewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesm10101.astraeus.apis.NasaIVLAPI
import com.jamesm10101.astraeus.data.NasaIVL
import com.jamesm10101.astraeus.data.NasaIVLImageCollection
import kotlinx.coroutines.launch
import kotlin.math.ceil

class IVLSearchResultsViewModel : ViewModel() {

    private val _query = MutableLiveData<String>("")
    private val _pageNumber = MutableLiveData<Int>(1)
    private val _maxPage = MutableLiveData<Int>(-1)
    private val _showGridView = MutableLiveData<Boolean>(true)
    private val _searchResults = MutableLiveData<NasaIVLImageCollection>()

    val query: LiveData<String> = _query
    val pageNumber: LiveData<Int> = _pageNumber
    val maxPage: LiveData<Int> = _maxPage
    val showGridView: LiveData<Boolean> = _showGridView
    val searchResults: LiveData<NasaIVLImageCollection> = _searchResults

    /**
     * Initializes the view model by setting the query
     * and calling the necessary initialization functions
     *
     * @param query
     */
    fun initialize(query: String = "") {
        _query.value = query
        loadSearchItems()
    }

    /**
     * Gets search results from NASA's IVL API using the imageSearch function.
     *
     * @see [com.jamesm10101.astraeus.apis.NasaIVLAPIService.imageSearch]
     *
     */
    private fun loadSearchItems() {
        viewModelScope.launch {
            try {
                val results: NasaIVL =
                    NasaIVLAPI.retrofitService.imageSearch(
                        query = _query.value,
                        page = _pageNumber.value!!
                    )

                _searchResults.value = results.collection

                if (_maxPage.value == -1) {
                    _maxPage.value =
                        ceil(results.collection.metadata.totalHits.toDouble() / 25).toInt()
                }

            } catch (e: Exception) {
                Log.e("NASAIVLImageSearch", e.message.toString())
            }
        }
    }

    /**
     * A click listener that changes the page number and loads in new images recycler view
     *
     * @return the click listener
     */
    fun onPageForwardClickListener(): View.OnClickListener {
        return View.OnClickListener {
            _pageNumber.value = _pageNumber.value!! + 1
            loadSearchItems()
        }
    }

    /**
     * A click listener that changes the page number and loads in new images recycler view
     *
     * @return the click listener
     */
    fun onPageBackClickListener(): View.OnClickListener {
        return View.OnClickListener {
            _pageNumber.value = _pageNumber.value!! - 1
            loadSearchItems()
        }
    }

    /**
     * A click listener that toggles between type of grid view.
     *
     * @return the click listener
     */
    fun onToggleGridViewClickListener(): View.OnClickListener {
        return View.OnClickListener {
            toggleGridView()
        }
    }

    /**
     * Toggles the value for the showGridView MutableLiveData variable.
     *
     */
    private fun toggleGridView() {
        _showGridView.value = _showGridView.value != true
    }
}