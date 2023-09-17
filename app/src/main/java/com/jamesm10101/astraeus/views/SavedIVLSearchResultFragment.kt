package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jamesm10101.astraeus.databinding.FragmentSavedIvlSearchResultBinding
import com.jamesm10101.astraeus.viewModels.SavedIVLSearchResultViewModel
import com.jamesm10101.astraeus.viewModels.SavedIVLSearchResultViewModelFactory

class SavedIVLSearchResultFragment : Fragment() {

    private val viewModel: SavedIVLSearchResultViewModel by viewModels<SavedIVLSearchResultViewModel>(
        factoryProducer = {
            SavedIVLSearchResultViewModelFactory(
                (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedIVLImageDao
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedIvlSearchResultBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

}