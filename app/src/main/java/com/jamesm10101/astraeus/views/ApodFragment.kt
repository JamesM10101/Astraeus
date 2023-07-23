package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.databinding.FragmentApodBinding
import com.jamesm10101.astraeus.viewModels.ApodViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel

class ApodFragment : Fragment() {

    private val viewModel: ApodViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentApodBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.apodResult = mainViewModel.apodResult
        binding.viewModel = viewModel

        return binding.root
    }

}