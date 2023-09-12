package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.FragmentApodExploreBinding
import com.jamesm10101.astraeus.viewModels.ApodExploreViewModel

class ApodExploreFragment : Fragment() {

    val viewModel: ApodExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentApodExploreBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.cycV_apodExploreItems)

        recycler.addOnScrollListener(onApodExploreScrollListener(recycler))
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
                    viewModel.loadApodImages {
                        // keep scrolled position
                        val state = layoutManager.onSaveInstanceState()
                        layoutManager.onRestoreInstanceState(state)
                    }
                }
            }
        }
    }
}