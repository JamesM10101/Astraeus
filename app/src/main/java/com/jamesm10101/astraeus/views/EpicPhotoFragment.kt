package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.databinding.FragmentEpicPhotoBinding
import com.jamesm10101.astraeus.viewModels.EpicPhotoViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel

class EpicPhotoFragment : MainBaseFragment() {

    private val viewModel: EpicPhotoViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEpicPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.epicPhoto = requireArguments().getParcelable("epicPhoto")
        binding.viewModel = viewModel
        binding.mainViewModel = mainViewModel

        return binding.root
    }

}