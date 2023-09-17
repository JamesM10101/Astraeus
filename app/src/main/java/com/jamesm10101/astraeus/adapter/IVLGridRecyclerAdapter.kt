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
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.databinding.IvlSearchResultItemBinding
import com.jamesm10101.astraeus.views.IVLSearchResultFragment

class IVLGridRecyclerAdapter :
    ListAdapter<NasaIVLImage, IVLGridRecyclerAdapter.IVLGridRecyclerPhotoViewHolder>(
        DiffCallback
    ) {
    companion object DiffCallback : DiffUtil.ItemCallback<NasaIVLImage>() {
        override fun areItemsTheSame(
            oldItem: NasaIVLImage,
            newItem: NasaIVLImage
        ): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(
            oldItem: NasaIVLImage,
            newItem: NasaIVLImage
        ): Boolean {
            return oldItem == newItem
        }
    }

    class IVLGridRecyclerPhotoViewHolder(private var binding: IvlSearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nasaIVLImage: NasaIVLImage) {
            binding.ivlItem = nasaIVLImage
            binding.showDetails = true
            binding.root.setOnClickListener {
                val context = binding.root.context
                val manager = (context as AppCompatActivity).supportFragmentManager
                try {
                    val bundle = Bundle()
                    bundle.putParcelable("searchResult", nasaIVLImage)

                    val fragment = IVLSearchResultFragment()
                    fragment.arguments = bundle

                    manager.beginTransaction().replace(R.id.main_fragment, fragment)
                        .addToBackStack(nasaIVLImage.imgUrl).commit()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Could not open image details", Toast.LENGTH_SHORT
                    ).show()
                    Log.e("SavedIVLDetails", e.message.toString())
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IVLGridRecyclerPhotoViewHolder {
        return IVLGridRecyclerPhotoViewHolder(
            IvlSearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: IVLGridRecyclerPhotoViewHolder, position: Int
    ) {
        val nasaIVLImage = getItem(position)
        holder.bind(nasaIVLImage)
    }

}