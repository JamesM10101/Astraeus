package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.FragmentHomeBinding
import com.jamesm10101.astraeus.viewModels.HomeViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mainViewModel = mainViewModel
        binding.apodClickListener = onApodComponentClick()

        return binding.root
    }

    private fun onApodComponentClick(): View.OnClickListener {
        return View.OnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main_fragment, ApodFragment())
                .addToBackStack(null).commit()
        }
    }

}