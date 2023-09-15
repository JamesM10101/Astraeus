package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.databinding.FragmentSavedMarsRoverPhotosBinding
import com.jamesm10101.astraeus.viewModels.SavedMarsRoverPhotosViewModel
import com.jamesm10101.astraeus.viewModels.SavedMarsRoverPhotosViewModelFactory

class SavedMarsRoverPhotosFragment : Fragment() {

    private val viewModel: SavedMarsRoverPhotosViewModel by viewModels<SavedMarsRoverPhotosViewModel> {
        SavedMarsRoverPhotosViewModelFactory(
            (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedMarsRoverPhotoDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedMarsRoverPhotosBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

}