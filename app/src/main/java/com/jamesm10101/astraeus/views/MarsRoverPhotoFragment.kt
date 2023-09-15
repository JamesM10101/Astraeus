package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverPhotoBinding
import com.jamesm10101.astraeus.viewModels.MainViewModel
import com.jamesm10101.astraeus.viewModels.MarsRoverPhotoViewModel
import com.jamesm10101.astraeus.viewModels.MarsRoverPhotoViewModelFactory

class MarsRoverPhotoFragment : MainBaseFragment() {

    private lateinit var viewModel: MarsRoverPhotoViewModel
    private lateinit var mainViewModel: MainViewModel

    private var marsRoverPhoto: MarsRoverPhoto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)

        @Suppress("DEPRECATION")
        marsRoverPhoto = requireArguments().getParcelable("marsRoverPhoto")

        viewModel = ViewModelProvider(
            this, MarsRoverPhotoViewModelFactory(
                marsRoverPhoto!!,
                (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedMarsRoverPhotoDao,
                resources
            )
        )[MarsRoverPhotoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMarsRoverPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel
        binding.viewModel = viewModel

        return binding.root
    }

}