package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.NasaIVLImage
import com.jamesm10101.astraeus.databinding.FragmentIvlSearchResultBinding
import com.jamesm10101.astraeus.viewModels.IVLSearchResultViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel
import java.text.SimpleDateFormat
import java.util.Locale

private const val ARG_SEARCH_RESULT = "searchResult"

class IVLSearchResultFragment : MainBaseFragment() {
    private var searchResult: NasaIVLImage? = null
    private val viewModel: IVLSearchResultViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchResult = it.getParcelable(ARG_SEARCH_RESULT)
        }
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentIvlSearchResultBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.viewModel = viewModel
        binding.searchResult = searchResult

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtDateCreated = view.findViewById<TextView>(R.id.txtV_created)

        try {
            val date = searchResult!!.data[0].dateCreated
            txtDateCreated.text = SimpleDateFormat("MMMM dd yyyy", Locale.US).format(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(date)!!
            );
        } catch (e: Exception) {
            Log.e("setCreatedDate", e.message.toString())
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchResult The search result item
         * @return A new instance of fragment IVLSearchResultFragment.
         */
        @JvmStatic
        fun newInstance(searchResult: String) =
            IVLSearchResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SEARCH_RESULT, searchResult)
                }
            }
    }
}