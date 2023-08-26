package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.adapter.RecyclerItemTouchListener
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.databinding.FragmentIvlSearchResultsBinding
import com.jamesm10101.astraeus.viewModels.IVLSearchResultsViewModel

private const val ARG_QUERY = "query"

class IVLSearchResultsFragment : Fragment() {
    private var param1: String? = null

    private val viewModel: IVLSearchResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_QUERY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentIvlSearchResultsBinding.inflate(inflater)
        binding.lifecycleOwner = requireActivity()
        binding.viewModel = viewModel

        param1?.let { viewModel.initialize(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cycResults = view.findViewById<RecyclerView>(R.id.cycV_searchResults)
        cycResults.addOnItemTouchListener(onResultsItemClick(cycResults))
    }

    private fun onResultsItemClick(
        recyclerView: RecyclerView
    ): RecyclerItemTouchListener {
        return RecyclerItemTouchListener(
            context, recyclerView, object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val searchResult: NasaIVLImage =
                        viewModel.searchResults.value!!.images[position]

                    val bundle = Bundle()
                    bundle.putParcelable("searchResult", searchResult)

                    val fragment = IVLSearchResultFragment()
                    fragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, fragment)
                        .addToBackStack(searchResult.imgUrl)
                        .commit()
                }

                override fun onLongClick(view: View?, position: Int) {

                }

            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 the search query
         * @return A new instance of fragment IVLSearchFragment.
         */
        @JvmStatic
        fun newInstance(param1: String) =
            IVLSearchResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUERY, param1)
                }
            }
    }
}