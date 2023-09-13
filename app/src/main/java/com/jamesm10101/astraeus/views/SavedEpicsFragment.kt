package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.FragmentSavedEpicsBinding
import com.jamesm10101.astraeus.viewModels.SavedEpicsViewModel
import com.jamesm10101.astraeus.viewModels.SavedEpicsViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SavedEpicsFragment : Fragment() {

    private val viewModel: SavedEpicsViewModel by viewModels<SavedEpicsViewModel> {
        SavedEpicsViewModelFactory(
            (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedEpicDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedEpicsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}