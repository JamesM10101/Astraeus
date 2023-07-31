package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databinding.ApodExploreItemBinding

class ApodExploreCarouselAdapter :
    ListAdapter<APOD, ApodExploreCarouselAdapter.ApodExplorePhotoViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<APOD>() {
        override fun areItemsTheSame(oldItem: APOD, newItem: APOD): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: APOD, newItem: APOD): Boolean {
            return oldItem == newItem
        }
    }

    class ApodExplorePhotoViewHolder(private var binding: ApodExploreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apod: APOD) {
            binding.apod = apod
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ApodExplorePhotoViewHolder {
        return ApodExplorePhotoViewHolder(
            ApodExploreItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: ApodExplorePhotoViewHolder, position: Int
    ) {
        val apod = getItem(position)
        holder.bind(apod)
    }
}

