package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.databinding.FragmentEpicPhotoBinding
import com.jamesm10101.astraeus.viewModels.EpicPhotoViewModel

class EpicPhotoFragment : Fragment() {

    private val viewModel: EpicPhotoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEpicPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.epicPhoto = requireArguments().getParcelable("epicPhoto")
        binding.viewModel = viewModel

        return binding.root
    }

}