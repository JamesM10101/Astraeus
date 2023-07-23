package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverPhotoBinding
import com.jamesm10101.astraeus.viewModels.MarsRoverPhotoViewModel

class MarsRoverPhotoFragment : Fragment() {

    private val viewModel: MarsRoverPhotoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMarsRoverPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.roverPhoto = requireArguments().getParcelable("marsRoverPhoto")
        binding.viewModel = viewModel
        viewModel.resources = resources

        return binding.root
    }

}