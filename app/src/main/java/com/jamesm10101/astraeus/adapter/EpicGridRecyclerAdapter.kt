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
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.databinding.EpicGridRecyclerItemBinding
import com.jamesm10101.astraeus.databinding.EpicImageCarouselItemBinding
import com.jamesm10101.astraeus.views.EpicPhotoFragment

class EpicGridRecyclerAdapter :
    ListAdapter<Epic, EpicGridRecyclerAdapter.EpicGridRecyclerPhotoViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Epic>() {
        override fun areItemsTheSame(oldItem: Epic, newItem: Epic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Epic, newItem: Epic): Boolean {
            return oldItem.image == newItem.image
        }
    }

    class EpicGridRecyclerPhotoViewHolder(private var binding: EpicGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(epicPhoto: Epic) {
            binding.epic = epicPhoto
            binding.root.setOnClickListener {
                val context = binding.root.context
                val manager = (context as AppCompatActivity).supportFragmentManager
                try {
                    val bundle = Bundle()
                    bundle.putParcelable("epicPhoto", epicPhoto)

                    val fragment = EpicPhotoFragment()
                    fragment.arguments = bundle

                    manager.beginTransaction().replace(R.id.main_fragment, fragment)
                        .addToBackStack(epicPhoto.caption).commit()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Could not open image details", Toast.LENGTH_SHORT
                    ).show()
                    Log.e("EpicDetails", e.message.toString())
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): EpicGridRecyclerPhotoViewHolder {
        return EpicGridRecyclerPhotoViewHolder(
            EpicGridRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: EpicGridRecyclerPhotoViewHolder, position: Int
    ) {
        val epicPhoto = getItem(position)
        holder.bind(epicPhoto)
    }

}