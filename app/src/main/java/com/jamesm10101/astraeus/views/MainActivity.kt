package com.jamesm10101.astraeus.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.ActivityMainBinding
import com.jamesm10101.astraeus.viewModels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val viewModel: MainViewModel by viewModels()
    private var navItemSelection = 0
    private var backstackCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the view model
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setContentView(binding.root)

        // set the navigation item listener
        val navView = findViewById<BottomNavigationView>(R.id.main_bottom_nav)
        navView.setOnItemSelectedListener(this)

        // Update selected menu item based on back stack
        supportFragmentManager.addOnBackStackChangedListener {
            updateNavItemSelection()
            updateSelectedMenuItem(navView)
        }
    }

    /**
     * Updates selected menu item based on the most recent navigation item selected.
     *
     * @param navView The view whose navigation to update.
     */
    private fun updateSelectedMenuItem(navView: BottomNavigationView) {
        if (supportFragmentManager.backStackEntryCount <= backstackCount) {
            navView.menu.getItem(navItemSelection).setChecked(true)
        }

        backstackCount = supportFragmentManager.backStackEntryCount
    }

    /**
     * Updates the most recent selected navigation item based on appearance in the backstack.
     *
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun updateNavItemSelection() {
        val backStackSize = supportFragmentManager.backStackEntryCount
        for (i in backStackSize - 1 downTo 0) {
            val backFragment = supportFragmentManager.getBackStackEntryAt(i)

            when (backFragment.name) {
                HomeFragment::class.java.name -> {
                    navItemSelection = 0
                    return
                }

                ExploreFragment::class.java.name -> {
                    navItemSelection = 1
                    return
                }

                SavedPostsFragment::class.java.name -> {
                    navItemSelection = 2
                    return
                }

                null -> {
                    navItemSelection = 0
                    return
                }
            }
        }
    }

    /**
     * Handles fragment transactions for the [BottomNavigationView] hosted in the [MainActivity].
     *
     * @param item The selected menu item from the [BottomNavigationView].
     * @return True if the fragment has been successfully changed, false otherwise.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        try {
            val supportFragmentManager = supportFragmentManager
            val transaction = supportFragmentManager.beginTransaction()

            val replacementFragment: Fragment = when (item.itemId) {
                R.id.menu_home -> HomeFragment()
                R.id.menu_explore -> ExploreFragment()
                R.id.menu_saved -> SavedPostsFragment()
                else -> supportFragmentManager.fragments[0]
            }

            // replace with the existing fragment if its already been visited
            val fragmentTag = replacementFragment::class.java.name
            val existingFragment: Fragment? = supportFragmentManager.findFragmentByTag(fragmentTag)

            if (existingFragment != null) {
                transaction.replace(R.id.main_fragment, existingFragment, fragmentTag)
            } else {
                transaction.replace(R.id.main_fragment, replacementFragment, fragmentTag)
            }

            transaction.addToBackStack(fragmentTag)
            transaction.commit()

            return true
        } catch (e: Exception) {
            Log.e("bottomNavItemSelected", e.message.toString())
            return false
        }
    }

}