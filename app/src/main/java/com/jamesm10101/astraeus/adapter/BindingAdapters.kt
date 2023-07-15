package com.jamesm10101.astraeus.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.MarsRoverPhoto


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.moon_phases)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsRoverPhoto>?) {
    recyclerView.adapter = RoverCarouselAdapter()
    val adapter = recyclerView.adapter as RoverCarouselAdapter
    adapter.submitList(data)
}