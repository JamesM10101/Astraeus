package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.jamesm10101.astraeus.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.btn_seeRoverImages).setOnClickListener {
            navigateToRoverImages()
        }

    }

    /**
     * Navigates to the [MarsRoverExploreFragment].
     */
    private fun navigateToRoverImages() {
        if (marsRoverPhoto != null) parentFragmentManager.beginTransaction().replace(
            R.id.main_fragment, MarsRoverExploreFragment.newInstance(marsRoverPhoto!!.rover.name)
        ).addToBackStack(MarsRoverExploreFragment::class.java.name).commit()
    }

}