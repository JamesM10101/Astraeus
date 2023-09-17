package com.jamesm10101.astraeus.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.databinding.ActivityMainBinding
import com.jamesm10101.astraeus.viewModels.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val viewModel: MainViewModel by viewModels()

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

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val transaction = supportFragmentManager.beginTransaction()
        
        val replacementFragment = when (item.itemId) {
            R.id.menu_home -> {
                HomeFragment()
            }

            R.id.menu_explore -> {
                ExploreFragment()
            }

            R.id.menu_saved -> {
                SavedPostsFragment()
            }

            else -> {
                supportFragmentManager.fragments[0]
            }
        }

        // switch fragment if they are not the same
        if (supportFragmentManager.fragments[0].javaClass != replacementFragment.javaClass) {
            transaction.replace(R.id.main_fragment, replacementFragment)
            transaction.addToBackStack(null)
            transaction.commit()

            return true
        }

        return false
    }

}