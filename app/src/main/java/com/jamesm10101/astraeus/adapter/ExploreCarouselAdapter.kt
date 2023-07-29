package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.ExploreSuggestionItem
import com.jamesm10101.astraeus.databinding.ExploreSuggestionItemBinding

class ExploreCarouselAdapter : ListAdapter<ExploreSuggestionItem,
        ExploreCarouselAdapter.ExplorePhotoViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<ExploreSuggestionItem>() {
        override fun areItemsTheSame(
            oldItem: ExploreSuggestionItem,
            newItem: ExploreSuggestionItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ExploreSuggestionItem,
            newItem: ExploreSuggestionItem
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    class ExplorePhotoViewHolder(private var binding: ExploreSuggestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exploreSuggestionItem: ExploreSuggestionItem) {
            binding.exploreItem = exploreSuggestionItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExploreCarouselAdapter.ExplorePhotoViewHolder {
        return ExplorePhotoViewHolder(
            ExploreSuggestionItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: ExploreCarouselAdapter.ExplorePhotoViewHolder,
        position: Int
    ) {
        val explorePhoto = getItem(position)
        holder.bind(explorePhoto)
    }
}

