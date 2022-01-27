package com.alfi.meals.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alfi.meals.R
import com.alfi.meals.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        initNavController()
    }

    private fun initNavController() {
        val bottomNav: BottomNavigationView = binding.bottomNav
        val navController: NavController = findNavController(R.id.nav_host_home)

        val appBarConf = AppBarConfiguration.Builder(
            R.id.seafood_navigation,
            R.id.favorite_navigation,
            R.id.vegetarian_navigation
        ).build()

        setupActionBarWithNavController(navController, appBarConf)
        bottomNav.setupWithNavController(navController)
    }
}