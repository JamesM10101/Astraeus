package com.jamesm10101.astraeus.adapter

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.ExploreSuggestionItem
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import java.lang.Exception

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

@BindingAdapter("exploreListData")
fun bindRecyclerViewExplore(recyclerView: RecyclerView, data: List<ExploreSuggestionItem>?) {
    recyclerView.adapter = ExploreCarouselAdapter()
    val adapter = recyclerView.adapter as ExploreCarouselAdapter
    adapter.submitList(data)
}

@BindingAdapter("apodExploreListData")
fun bindRecyclerViewApodExplore(recyclerView: RecyclerView, data: List<APOD>?) {
    try {
        val adapter = ApodExploreCarouselAdapter()
        val currApodList = adapter.currentList

        recyclerView.adapter = adapter
        adapter.submitList(data)

        if (currApodList.isNotEmpty()) {
            adapter.notifyItemRangeChanged(currApodList.size - 1, data!!.size - 1)
        }

    } catch (e: Exception) {
        Log.d("bindApodExplore", e.message.toString())
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
        Log.d("bindMarsRoverExplore", e.message.toString())
    }
}