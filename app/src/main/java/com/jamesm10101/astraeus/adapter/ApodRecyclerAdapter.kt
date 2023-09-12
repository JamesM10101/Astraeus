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
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databinding.ApodExploreItemBinding
import com.jamesm10101.astraeus.views.ApodFragment

class ApodRecyclerAdapter :
    ListAdapter<APOD, ApodRecyclerAdapter.ApodExplorePhotoViewHolder>(DiffCallback) {
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
            binding.root.setOnClickListener {
                val context = binding.root.context
                val manager = (context as AppCompatActivity).supportFragmentManager
                try {
                    val bundle = Bundle()
                    bundle.putParcelable("apod", apod)

                    val fragment = ApodFragment()
                    fragment.arguments = bundle

                    manager.beginTransaction().replace(R.id.main_fragment, fragment)
                        .addToBackStack(apod.title).commit()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Could not open image details", Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ApodDetails", e.message.toString())
                }
            }
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

