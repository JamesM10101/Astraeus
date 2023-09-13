package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverPhotoBinding
import com.jamesm10101.astraeus.viewModels.MainViewModel
import com.jamesm10101.astraeus.viewModels.MarsRoverPhotoViewModel

class MarsRoverPhotoFragment : MainBaseFragment() {

    private val viewModel: MarsRoverPhotoViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMarsRoverPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel
        binding.viewModel = viewModel
        viewModel.resources = resources

        @Suppress("DEPRECATION")
        binding.roverPhoto = requireArguments().getParcelable("marsRoverPhoto")

        return binding.root
    }

}