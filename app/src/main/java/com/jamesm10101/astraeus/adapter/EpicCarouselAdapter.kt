package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.databinding.EpicImageCarouselItemBinding

class EpicCarouselAdapter :
    ListAdapter<Epic, EpicCarouselAdapter.EpicPhotoViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Epic>() {
        override fun areItemsTheSame(oldItem: Epic, newItem: Epic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Epic, newItem: Epic): Boolean {
            return oldItem.image == newItem.image
        }
    }

    class EpicPhotoViewHolder(private var binding: EpicImageCarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(epicPhoto: Epic) {
            binding.photo = epicPhoto
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): EpicPhotoViewHolder {
        return EpicPhotoViewHolder(
            EpicImageCarouselItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: EpicPhotoViewHolder, position: Int
    ) {
        val epicPhoto = getItem(position)
        holder.bind(epicPhoto)
    }

}