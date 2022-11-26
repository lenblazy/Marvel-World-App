package com.mwabonje.marvelworld.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.mwabonje.marvelworld.databinding.ActivityMainBinding

/**
 * Single Activity Class
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.main_navigation) as NavHostFragment
//        val navController = navHostFragment.navController

        setContentView(binding.root)
    }
}