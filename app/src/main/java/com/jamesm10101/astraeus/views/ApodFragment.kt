package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databinding.FragmentApodBinding
import com.jamesm10101.astraeus.viewModels.ApodViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel

class ApodFragment : MainBaseFragment() {

    private val viewModel: ApodViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        @Suppress("DEPRECATION")
        val apod: APOD? = arguments?.getParcelable("apod")

        val binding = FragmentApodBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.apodResult = when (apod != null) {
            true -> MutableLiveData(apod)
            false -> mainViewModel.apodResult
        }
        binding.viewModel = viewModel

        return binding.root
    }

}