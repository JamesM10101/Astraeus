package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.MarsRoverExploreItemBinding

class MarsRoverExploreAdapter :
    ListAdapter<MarsRoverPhoto, MarsRoverExploreAdapter.MarsRoverExplorePhotoViewHolder>(
        DiffCallback
    ) {
    companion object DiffCallback : DiffUtil.ItemCallback<MarsRoverPhoto>() {
        override fun areItemsTheSame(oldItem: MarsRoverPhoto, newItem: MarsRoverPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsRoverPhoto, newItem: MarsRoverPhoto): Boolean {
            return oldItem == newItem
        }
    }

    class MarsRoverExplorePhotoViewHolder(private var binding: MarsRoverExploreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsRoverPhoto: MarsRoverPhoto) {
            binding.marsRoverPhoto = marsRoverPhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MarsRoverExplorePhotoViewHolder {
        return MarsRoverExplorePhotoViewHolder(
            MarsRoverExploreItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: MarsRoverExplorePhotoViewHolder, position: Int
    ) {
        val marsRoverPhoto = getItem(position)
        holder.bind(marsRoverPhoto)
    }
}

