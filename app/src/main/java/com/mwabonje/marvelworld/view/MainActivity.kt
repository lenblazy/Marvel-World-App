package com.mwabonje.marvelworld.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mwabonje.marvelworld.R
import com.mwabonje.marvelworld.databinding.ActivityMainBinding

/**
 * Single Activity Class
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//
//        // This line is only necessary if using the default action bar.
//        setupActionBarWithNavController(navController, appBarConfiguration)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.main_navigation) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        setContentView(binding.root)
    }
}