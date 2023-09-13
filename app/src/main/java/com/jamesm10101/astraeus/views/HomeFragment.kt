package com.jamesm10101.astraeus.views

import com.jamesm10101.astraeus.adapter.RecyclerItemTouchListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.Epic
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.FragmentHomeBinding
import com.jamesm10101.astraeus.viewModels.HomeViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel
import kotlin.Exception

class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.mainViewModel = mainViewModel
        binding.apodClickListener = onApodComponentClick()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cycRover1 = view.findViewById<View>(R.id.rover1_component)
            .findViewById<RecyclerView>(R.id.cycV_roverPhotos)
        cycRover1.addOnItemTouchListener(onRoverCarouselItemClick(cycRover1, 1))

        val cycRover2 = view.findViewById<View>(R.id.rover2_component)
            .findViewById<RecyclerView>(R.id.cycV_roverPhotos)
        cycRover2.addOnItemTouchListener(onRoverCarouselItemClick(cycRover2, 2))

        val cycEpic = view.findViewById<View>(R.id.epic_component)
            .findViewById<RecyclerView>(R.id.cycV_epicPhotos)
        cycEpic.addOnItemTouchListener(onEpicCarouselItemClick(cycEpic))

    }

    private fun onEpicCarouselItemClick(
        recyclerView: RecyclerView
    ): RecyclerItemTouchListener {
        return RecyclerItemTouchListener(
            context, recyclerView, object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val epicImg: Epic = viewModel.epicLatest.value!![position]

                    try {
                        val bundle = Bundle()
                        bundle.putParcelable("epicPhoto", epicImg)

                        val fragment = EpicPhotoFragment()
                        fragment.arguments = bundle

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_fragment, fragment)
                            .addToBackStack(null).commit()

                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            "Could not open image details",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("EpicImageDetails", e.message.toString())
                    }

                }

                override fun onLongClick(view: View?, position: Int) {}
            }
        )
    }

    private fun onRoverCarouselItemClick(
        recyclerView: RecyclerView,
        roverNum: Int
    ): RecyclerItemTouchListener {
        return RecyclerItemTouchListener(
            context,
            recyclerView,
            object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {

                    lateinit var roverImg: MarsRoverPhoto
                    lateinit var fragment: Fragment
                    val bundle = Bundle()

                    try {
                        if (roverNum == 1) {
                            roverImg = viewModel.rover1photos.value!!.photos[position]
                        } else if (roverNum == 2) {
                            roverImg = viewModel.rover2photos.value!!.photos[position]
                        }

                        bundle.putParcelable("marsRoverPhoto", roverImg)
                        fragment = MarsRoverPhotoFragment()
                        fragment.arguments = bundle
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_fragment, fragment)
                            .addToBackStack(null).commit()

                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            "Could not open image details",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("RoverImgDetails", e.message.toString())
                    }

                }

                override fun onLongClick(view: View?, position: Int) {}
            })
    }

    private fun onApodComponentClick(): View.OnClickListener {
        return View.OnClickListener {
            try {
                val bundle = Bundle()
                bundle.putParcelable("apod", mainViewModel.apodResult.value!!)

                val fragment = ApodFragment()
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction().replace(R.id.main_fragment, fragment)
                    .addToBackStack(null).commit()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Could not Apod details.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("ApodDetails", e.message.toString())
            }
        }
    }

}