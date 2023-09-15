package com.jamesm10101.astraeus.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.MarsRoverExploreItemBinding
import com.jamesm10101.astraeus.views.MarsRoverPhotoFragment

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
            binding.root.setOnClickListener {
                val context = binding.root.context
                val manager = (context as AppCompatActivity).supportFragmentManager
                try {
                    val bundle = Bundle()
                    bundle.putParcelable("marsRoverPhoto", marsRoverPhoto)

                    val fragment = MarsRoverPhotoFragment()
                    fragment.arguments = bundle

                    manager.beginTransaction().replace(R.id.main_fragment, fragment)
                        .addToBackStack(marsRoverPhoto.id.toString()).commit()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Could not open image details", Toast.LENGTH_SHORT
                    ).show()
                    Log.e("MarsRoverPhotoDetails", e.message.toString())
                }
            }
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

