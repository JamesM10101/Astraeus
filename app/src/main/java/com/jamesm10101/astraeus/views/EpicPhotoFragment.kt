package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.databinding.FragmentEpicPhotoBinding
import com.jamesm10101.astraeus.viewModels.EpicPhotoViewModel
import com.jamesm10101.astraeus.viewModels.EpicPhotoViewModelFactory
import com.jamesm10101.astraeus.viewModels.MainViewModel

class EpicPhotoFragment : MainBaseFragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: EpicPhotoViewModel

    private var epic: Epic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)

        @Suppress("DEPRECATION")
        epic = requireArguments().getParcelable("epicPhoto")

        viewModel = ViewModelProvider(
            this, EpicPhotoViewModelFactory(
                epic!!,
                (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedEpicDao
            )
        )[EpicPhotoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEpicPhotoBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mainViewModel = mainViewModel

        return binding.root
    }

}