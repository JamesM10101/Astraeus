package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.databinding.FragmentIvlSearchResultBinding
import com.jamesm10101.astraeus.viewModels.IVLSearchResultViewModel
import com.jamesm10101.astraeus.viewModels.IVLSearchResultViewModelFactory
import com.jamesm10101.astraeus.viewModels.MainViewModel
import java.text.SimpleDateFormat
import java.util.Locale

private const val ARG_SEARCH_RESULT = "searchResult"

class IVLSearchResultFragment : FullImageFragment() {
    private var searchResult: NasaIVLImage? = null
    private lateinit var viewModel: IVLSearchResultViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        searchResult = requireArguments().getParcelable(ARG_SEARCH_RESULT)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)

        viewModel = ViewModelProvider(
            this, IVLSearchResultViewModelFactory(
                searchResult!!,
                (requireActivity().application as AstraeusApplication).savedPostsDatabase.savedIVLImageDao
            )
        )[IVLSearchResultViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentIvlSearchResultBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtDateCreated = view.findViewById<TextView>(R.id.txtV_created)

        try {
            val date = searchResult!!.data[0].dateCreated
            txtDateCreated.text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(date)!!
            )
        } catch (e: Exception) {
            Log.e("setCreatedDate", e.message.toString())
        }

        view.findViewById<MaterialButton>(R.id.btn_downloadImage).setOnClickListener {
            handleUserRequestedImageDownload(searchResult!!.links[0].thumbnail)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchResult The search result item
         * @return A new instance of fragment IVLSearchResultFragment.
         */
        @JvmStatic
        fun newInstance(searchResult: String) = IVLSearchResultFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_SEARCH_RESULT, searchResult)
            }
        }
    }
}