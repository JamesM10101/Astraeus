package com.jamesm10101.astraeus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.data.MarsRoverInstrument
import com.jamesm10101.astraeus.databinding.MarsRoverInstrumentDetailsComponentBinding

class MarsRoverInstrumentsListAdapter :
    ListAdapter<MarsRoverInstrument, MarsRoverInstrumentsListAdapter.MarsRoverInstrumentsViewHolder>(
        DiffCallback
    ) {
    companion object DiffCallback : DiffUtil.ItemCallback<MarsRoverInstrument>() {
        override fun areItemsTheSame(
            oldItem: MarsRoverInstrument,
            newItem: MarsRoverInstrument
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MarsRoverInstrument,
            newItem: MarsRoverInstrument
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MarsRoverInstrumentsViewHolder(
        private var binding: MarsRoverInstrumentDetailsComponentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsRoverInstrument: MarsRoverInstrument) {
            binding.instrument = marsRoverInstrument
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MarsRoverInstrumentsViewHolder {
        return MarsRoverInstrumentsViewHolder(
            MarsRoverInstrumentDetailsComponentBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(
        holder: MarsRoverInstrumentsViewHolder, position: Int
    ) {
        val marsRoverInstrument = getItem(position)
        holder.bind(marsRoverInstrument)
    }
}