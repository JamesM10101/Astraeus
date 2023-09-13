package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.databinding.FragmentSavedApodsBinding
import com.jamesm10101.astraeus.viewModels.SavedApodsViewModel
import com.jamesm10101.astraeus.viewModels.SavedApodsViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SavedApodsFragment : Fragment() {

    private val viewModel: SavedApodsViewModel by viewModels<SavedApodsViewModel> {
        SavedApodsViewModelFactory(
            (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedApodDao
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedApodsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

}