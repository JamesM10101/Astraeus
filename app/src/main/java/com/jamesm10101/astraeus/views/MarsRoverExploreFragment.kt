package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverExploreBinding
import com.jamesm10101.astraeus.viewModels.MarsRoverExploreViewModel

private const val ARG_ROVER_NAME = "roverName"

class MarsRoverExploreFragment : Fragment() {

    private val viewModel: MarsRoverExploreViewModel by viewModels()
    private lateinit var roverName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roverName = it.getString(ARG_ROVER_NAME).toString()
            viewModel.initialize(roverName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMarsRoverExploreBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set the scroll and item touch listeners
        val cycRoverPhotos = view.findViewById<RecyclerView>(R.id.cycV_roverPhotos)
        cycRoverPhotos.addOnScrollListener(viewModel.onRoverExploreScrollListener(cycRoverPhotos))
        cycRoverPhotos.addOnItemTouchListener(
            viewModel.onMarsRoverExploreItemClick(
                cycRoverPhotos,
                parentFragmentManager
            )
        )

        // set the onClick listener
        val btnRoverDetails = view.findViewById<MaterialButton>(R.id.btn_seeRoverDetails)
        btnRoverDetails.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("roverDetails", viewModel.roverDetails.value)

            val fragment = MarsRoverInfoFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null).commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param roverName The name of the rover
         * @return A new instance of fragment RoverExploreFragment.
         */
        @JvmStatic
        fun newInstance(roverName: String) =
            MarsRoverExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ROVER_NAME, roverName)
                }
            }
    }
}