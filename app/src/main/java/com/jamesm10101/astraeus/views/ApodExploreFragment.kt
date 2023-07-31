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
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databinding.FragmentApodExploreBinding
import com.jamesm10101.astraeus.viewModels.ApodExploreViewModel
import java.lang.Exception

class ApodExploreFragment : Fragment() {

    val viewModel: ApodExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentApodExploreBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.cycV_apodExploreItems)

        recycler.addOnScrollListener(onApodExploreScrollListener(recycler))
        recycler.addOnItemTouchListener(onApodExploreItemClick(recycler))
    }

    private fun onApodExploreScrollListener(recyclerView: RecyclerView): RecyclerView.OnScrollListener {
        val layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.exploreRecyclerSpan))
        recyclerView.layoutManager = layoutManager

        // load new images when bottom is reached
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    val oldSize: Int = viewModel.apodImages.value?.size!!
                    val newSize: Int = oldSize.plus(7)

                    val state = layoutManager.onSaveInstanceState()

                    viewModel.loadApodImages {
                        // keep scrolled position
                        recyclerView.adapter?.notifyItemRangeInserted(oldSize, newSize)
                        layoutManager.onRestoreInstanceState(state)
                    }

                }
            }
        }
    }

    private fun onApodExploreItemClick(
        recyclerView: RecyclerView
    ): RecyclerItemTouchListener {

        // switch to apod fragment
        return RecyclerItemTouchListener(
            context,
            recyclerView,
            object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val apod: APOD = viewModel.apodImages.value!![position]

                    try {
                        val bundle = Bundle()
                        bundle.putParcelable("apod", apod)

                        val fragment = ApodFragment()
                        fragment.arguments = bundle

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main_fragment, fragment).addToBackStack(apod.title)
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

}