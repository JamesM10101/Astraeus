package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.databinding.IvlSearchResultItemBinding

class IVLSearchResultsAdapter :
    ListAdapter<NasaIVLImage, IVLSearchResultsAdapter.NasaIVLImageViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<NasaIVLImage>() {
        override fun areItemsTheSame(oldItem: NasaIVLImage, newItem: NasaIVLImage): Boolean {
            return oldItem.imgUrl == newItem.imgUrl
        }

        override fun areContentsTheSame(oldItem: NasaIVLImage, newItem: NasaIVLImage): Boolean {
            return oldItem == newItem
        }
    }

    class NasaIVLImageViewHolder(
        private var binding: IvlSearchResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(nasaIVlImage: NasaIVLImage) {
            binding.ivlItem = nasaIVlImage
            binding.showDetails = true
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NasaIVLImageViewHolder {
        return NasaIVLImageViewHolder(
            IvlSearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: NasaIVLImageViewHolder, position: Int
    ) {
        val nasaIVLImage = getItem(position)
        holder.bind(nasaIVLImage)
    }
}