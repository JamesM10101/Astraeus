package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.adapter.RecyclerItemTouchListener
import com.jamesm10101.astraeus.data.MarsRoverPhoto
import com.jamesm10101.astraeus.databinding.FragmentMarsRoverExploreBinding
import com.jamesm10101.astraeus.viewModels.MarsRoverExploreViewModel
import java.lang.Exception

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

        val cycRoverPhotos = view.findViewById<RecyclerView>(R.id.cycV_roverPhotos)
        cycRoverPhotos.addOnScrollListener(onRoverExploreScrollListener(cycRoverPhotos))
        cycRoverPhotos.addOnItemTouchListener(onMarsRoverExploreItemClick(cycRoverPhotos))
    }

    /**
     * Loads in the rover photos when the bottom of the recycler view is reached
     *
     * @param recyclerView the recycler view to listen to
     * @return the OnScrollListener
     */
    private fun onRoverExploreScrollListener(recyclerView: RecyclerView): RecyclerView.OnScrollListener {
        val layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.exploreRecyclerSpan))
        recyclerView.layoutManager = layoutManager

        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMarsRoverImages {
                        // keep scrolled position
                        val state = layoutManager.onSaveInstanceState()
                        layoutManager.onRestoreInstanceState(state)
                    }
                }
            }
        }
    }

    /**
     * Listens for clicks on the recycler view's items and navigates to the MarsRoverPhotoFragment
     *
     * @see MarsRoverPhotoFragment
     *
     * @param recyclerView the recycler view to listen to clicks on
     * @return the RecyclerItemTouchListener
     */
    private fun onMarsRoverExploreItemClick(
        recyclerView: RecyclerView
    ): RecyclerItemTouchListener {

        // switch to apod fragment
        return RecyclerItemTouchListener(
            context,
            recyclerView,
            object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val roverPhoto: MarsRoverPhoto = viewModel.roverPhotos.value!![position]

                    try {
                        val bundle = Bundle()
                        bundle.putParcelable("marsRoverPhoto", roverPhoto)

                        val fragment = MarsRoverPhotoFragment()
                        fragment.arguments = bundle

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_fragment, fragment)
                            .addToBackStack(roverPhoto.id.toString())
                            .commit()
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(), "Could not open image details", Toast.LENGTH_SHORT
                        ).show()
                        Log.e("ApodExploreDetails", e.message.toString())
                    }

                }

                override fun onLongClick(view: View?, position: Int) {}
            })
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