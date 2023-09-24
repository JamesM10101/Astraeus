package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.adapter.RecyclerItemTouchListener
import com.jamesm10101.astraeus.data.ExploreSuggestionEnums
import com.jamesm10101.astraeus.data.ExploreSuggestionItem
import com.jamesm10101.astraeus.data.ExploreSuggestionItems
import com.jamesm10101.astraeus.databinding.FragmentExploreBinding


class ExploreFragment : FullImageFragment() {

    private lateinit var exploreItems: List<ExploreSuggestionItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentExploreBinding.inflate(inflater)

        exploreItems = ExploreSuggestionItems.getItems()
        binding.exploreItems = exploreItems

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cycExploreItems = view.findViewById<RecyclerView>(R.id.cycV_exploreItems)
        cycExploreItems.addOnItemTouchListener(onExploreCarouselItemClick(cycExploreItems))

        val searchView = view.findViewById<TextInputEditText>(R.id.tiet_ivlSearch)
        searchView.setOnEditorActionListener { textView, actionId, event ->
            submitSearchQuery(textView, actionId, event)
        };
    }

    /**
     * Handles the submission of a search query triggered by a keyboard action or button click.
     *
     * @param textView The TextView containing the search query.
     * @param actionId The ID of the keyboard action (e.g., IME_ACTION_GO) that triggered the submission.
     * @param event The KeyEvent that triggered the submission (can be null if not from a KeyEvent).
     *
     * @return `true` if the submission was handled successfully, `false` otherwise.
     */
    private fun submitSearchQuery(textView: TextView, actionId: Int, event: KeyEvent?): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_GO) {

            val bundle = Bundle()
            bundle.putString("query", textView.text.toString())

            val fragment = IVLSearchResultsFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction().replace(R.id.main_fragment, fragment)
                .addToBackStack(textView.text.toString()).commit()

            true
        } else false
    }

    /**
     * Creates a [RecyclerItemTouchListener] to handle item clicks within an Explore carousel RecyclerView.
     *
     * @param recyclerView The RecyclerView that displays the Explore carousel items.
     * @return A [RecyclerItemTouchListener] to handle item clicks.
     */
    private fun onExploreCarouselItemClick(
        recyclerView: RecyclerView
    ): RecyclerItemTouchListener {
        return RecyclerItemTouchListener(
            context, recyclerView, object : RecyclerItemTouchListener.ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val exploreItem: ExploreSuggestionItem = exploreItems[position]

                    try {
                        when (exploreItem.type) {
                            ExploreSuggestionEnums.APOD -> {
                                parentFragmentManager.beginTransaction()
                                    .replace(R.id.main_fragment, ApodExploreFragment())
                                    .addToBackStack("apodCollection").commit()
                            }

                            ExploreSuggestionEnums.ROVER -> {
                                parentFragmentManager.beginTransaction()
                                    .replace(
                                        R.id.main_fragment,
                                        MarsRoverExploreFragment.newInstance(exploreItem.name)
                                    )
                                    .addToBackStack("roverCollection").commit()
                            }

                            ExploreSuggestionEnums.SEARCH -> {
                                parentFragmentManager.beginTransaction()
                                    .replace(
                                        R.id.main_fragment,
                                        IVLSearchResultsFragment.newInstance(exploreItem.name.lowercase())
                                    )
                                    .addToBackStack("nasaIVLSearch").commit()
                            }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            "Could not open page.",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("ExplorePageItem", e.message.toString())
                    }

                }

                override fun onLongClick(view: View?, position: Int) {}
            }
        )
    }

}