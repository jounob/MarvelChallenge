package com.esther.intermediachallenge.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        navController = findNavController(R.id.navHostFragment)
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_characters, R.id.navigation_events))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.btnNavView.itemIconTintList = null
        val bottomNavigationView = binding.btnNavView
        setupWithNavController(bottomNavigationView, navController)
    }

    fun setToolbarTitle(title: String = getString(R.string.title_app)) {
        binding.tvToolBarTitle.text = title
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun buttonNavigationHide(){
        binding.btnNavView.isVisible = false
    }
}
