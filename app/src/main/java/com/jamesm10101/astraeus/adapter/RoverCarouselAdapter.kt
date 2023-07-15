package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.RoverImageCarouselItemBinding

class RoverCarouselAdapter : ListAdapter<MarsRoverPhoto,
        RoverCarouselAdapter.RoverPhotoViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<MarsRoverPhoto>() {
        override fun areItemsTheSame(oldItem: MarsRoverPhoto, newItem: MarsRoverPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsRoverPhoto, newItem: MarsRoverPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    class RoverPhotoViewHolder(private var binding: RoverImageCarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhoto: MarsRoverPhoto) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoverCarouselAdapter.RoverPhotoViewHolder {
        return RoverPhotoViewHolder(
            RoverImageCarouselItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: RoverCarouselAdapter.RoverPhotoViewHolder,
        position: Int
    ) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

}
