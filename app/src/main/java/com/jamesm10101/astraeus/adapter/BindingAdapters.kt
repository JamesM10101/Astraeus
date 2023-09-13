package com.jamesm10101.astraeus.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.ExploreSuggestionItem
import com.jamesm10101.astraeus.data.MarsRoverCam
import com.jamesm10101.astraeus.data.MarsRoverInstrument
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.data.NasaIVLImageCollection
import com.jamesm10101.astraeus.databinding.MarsRoverCamsChipBinding
import kotlin.Exception

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.moon_phases)
            error(R.drawable.ic_broken_image)
            listener(onError = { _, error ->
                Log.e(
                    "coil error", error.throwable.message.toString()
                )
            })
        }
    }
}

@BindingAdapter("roverListData")
fun bindRecyclerViewRover(recyclerView: RecyclerView, data: List<MarsRoverPhoto>?) {
    recyclerView.adapter = RoverCarouselAdapter()
    val adapter = recyclerView.adapter as RoverCarouselAdapter
    adapter.submitList(data)
}

@BindingAdapter("epicListData")
fun bindRecyclerViewEpic(recyclerView: RecyclerView, data: List<Epic>?) {
    recyclerView.adapter = EpicCarouselAdapter()
    val adapter = recyclerView.adapter as EpicCarouselAdapter
    adapter.submitList(data)
}

@BindingAdapter("epicGridRecyclerListData")
fun bindGridRecyclerViewEpic(recyclerView: RecyclerView, data: List<Epic>?) {
    try {
        val adapter = EpicGridRecyclerAdapter()
        val currList = adapter.currentList

        recyclerView.adapter = adapter
        adapter.submitList(data)

        if (currList.isNotEmpty()) {
            adapter.notifyItemRangeChanged(currList.size - 1, data!!.size - 1)
        }
    } catch (e: Exception) {
        Log.e("bindEpicGridRecycler", e.message.toString())
    }
}

@BindingAdapter("exploreListData")
fun bindRecyclerViewExplore(recyclerView: RecyclerView, data: List<ExploreSuggestionItem>?) {
    recyclerView.adapter = ExploreCarouselAdapter()
    val adapter = recyclerView.adapter as ExploreCarouselAdapter
    adapter.submitList(data)
}

@BindingAdapter("apodListData")
fun bindRecyclerViewApodExplore(recyclerView: RecyclerView, data: List<APOD>?) {
    try {
        val adapter = ApodRecyclerAdapter()
        val currApodList = adapter.currentList

        recyclerView.adapter = adapter
        adapter.submitList(data)

        if (currApodList.isNotEmpty()) {
            adapter.notifyItemRangeChanged(currApodList.size - 1, data!!.size - 1)
        }

    } catch (e: Exception) {
        Log.e("bindApodExplore", e.message.toString())
    }
}

@BindingAdapter("marsRoverExploreListData")
fun bindMarsRoverExplore(recyclerView: RecyclerView, data: List<MarsRoverPhoto>?) {
    try {
        val adapter = MarsRoverExploreAdapter()
        val currList = adapter.currentList

        recyclerView.adapter = adapter
        adapter.submitList(data)

        if (currList.isNotEmpty()) {
            adapter.notifyItemRangeChanged(currList.size - 1, data!!.size - 1)
        }

    } catch (e: Exception) {
        Log.e("bindMarsRoverExplore", e.message.toString())
    }
}

@BindingAdapter("marsRoverCamsChipListData")
fun bindMarsRoverCamsChip(chipGroup: ChipGroup, data: List<MarsRoverCam>?) {
    try {
        if (!data.isNullOrEmpty()) {
            chipGroup.removeAllViews()

            val context = chipGroup.context
            val inflater = LayoutInflater.from(context)

            // All images chip
            val allChip = MarsRoverCamsChipBinding.inflate(inflater, chipGroup, false)
            allChip.camName = context.getString(R.string.all)
            (allChip.root as Chip).isChecked = true
            chipGroup.addView(allChip.root)

            // add chips to the group
            for (cam in data) {
                val chip = MarsRoverCamsChipBinding.inflate(inflater, chipGroup, false)
                chip.camName = cam.name
                chipGroup.addView(chip.root)
            }
        }
    } catch (e: Exception) {
        Log.e("bindMarsRoverCamsChip", e.message.toString())
    }
}

@BindingAdapter("onCheckedChanged")
fun bindOnCheckChanged(chipGroup: ChipGroup, listener: ChipGroup.OnCheckedStateChangeListener) {
    try {
        chipGroup.setOnCheckedStateChangeListener(listener)
    } catch (e: Exception) {
        Log.e("bindOnCheckedChanged", e.message.toString())
    }
}

@BindingAdapter("marsRoverInstruments")
fun bindMarsRoverInstrument(
    recyclerView: RecyclerView, data: List<MarsRoverInstrument>
) {
    try {
        recyclerView.adapter = MarsRoverInstrumentsListAdapter()
        val adapter = recyclerView.adapter as MarsRoverInstrumentsListAdapter
        adapter.submitList(data)
    } catch (e: Exception) {
        Log.e("bindMarsRoverInstrument", e.message.toString())
    }
}

@BindingAdapter("chipListData")
fun bindChipListData(chipGroup: ChipGroup, data: List<String>?) {
    try {
        if (!data.isNullOrEmpty()) {
            chipGroup.removeAllViews()

            val context = chipGroup.context

            // add chips to the group
            for (str in data) {
                val chip = Chip(context)
                chip.text = str
                chipGroup.addView(chip)
            }
        }
    } catch (e: Exception) {
        Log.e("bindMarsRoverCamsChip", e.message.toString())
    }
}

@BindingAdapter("ivlSearchResults")
fun bindIVLSearchResults(
    recyclerView: RecyclerView, data: NasaIVLImageCollection?
) {
    try {
        recyclerView.adapter = IVLSearchResultsAdapter()
        val adapter = recyclerView.adapter as IVLSearchResultsAdapter
        adapter.submitList(data?.images)
    } catch (e: Exception) {
        Log.e("bindIVLSearchResults", e.message.toString())
    }
}

@BindingAdapter("ivlSpanCountInt")
fun bindIVLSpanCountInt(recyclerView: RecyclerView, size: Int) {
    try {
        val context = recyclerView.context

        if (size <= 1) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(size, StaggeredGridLayoutManager.VERTICAL)
        }

    } catch (e: Exception) {
        Log.e("ivlSpanCountInt", e.message.toString())
    }
}

@BindingAdapter("printData")
fun bindTestPrintData(view: View, data: Any?) {
    try {
        Log.d("data", data.toString())
    } catch (e: Exception) {
        Log.e("testPrintData", e.message.toString())
    }
}
