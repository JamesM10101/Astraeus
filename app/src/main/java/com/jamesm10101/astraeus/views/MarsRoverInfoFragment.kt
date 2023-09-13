package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamesm10101.astraeus.data.MarsRover
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverInfoBinding
import com.jamesm10101.astraeus.utils.getRoverNameEnum
import com.jamesm10101.astraeus.utils.getRoverSpecs

private const val ARG_ROVER_DETAILS = "roverDetails"

class MarsRoverInfoFragment : Fragment() {
    private var roverDetails: MarsRover? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            roverDetails = it.getParcelable(ARG_ROVER_DETAILS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMarsRoverInfoBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.roverDetails = roverDetails
        binding.marsRoverSpecs = getRoverSpecs(getRoverNameEnum(roverDetails!!.name))

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 the Mars rover to view the details of.
         * @return A new instance of fragment MarsRoverInfoFragment.
         */
        @JvmStatic
        fun newInstance(roverDetails: MarsRover) =
            MarsRoverInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ROVER_DETAILS, roverDetails)
                }
            }
    }
}