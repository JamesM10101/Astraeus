package com.jamesm10101.astraeus.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.viewModels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SavedPostsFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private lateinit var adapter: SavedPagePagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // views
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs_saved)
        viewPager = view.findViewById<ViewPager2>(R.id.pager_saved)

        // set pager adapter
        adapter = SavedPagePagerAdapter(this)
        viewPager.adapter = adapter

        val tabNames = resources.getStringArray(R.array.saved_posts_tab_names)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    class SavedPagePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SavedApodsFragment()

                1 -> SavedEpicsFragment()

                2 -> SavedMarsRoverPhotosFragment()

                3 -> SavedIVLSearchResultFragment()

                else -> ConstructionFragment()
            }
        }
    }

}