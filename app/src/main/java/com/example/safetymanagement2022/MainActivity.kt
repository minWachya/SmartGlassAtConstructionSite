package com.example.safetymanagement2022

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.safetymanagement2022.databinding.ActivityMainBinding
import com.example.safetymanagement2022.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.navigationMain.itemIconTintList = null    // Theme 의 color 로 아이콘 색 표시 X, drawable 그대로 사용

        // FragmentContainerView 의 NavController 가져와서 bottomNavigation 에 전달
        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            binding.navigationMain.setupWithNavController(it)
            initDestinationListener()
        }
    }

    private fun initDestinationListener() {
        supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
            ?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_list ||
                destination.id == R.id.navigation_home ||
                destination.id == R.id.navigation_setting
            ) {
                binding.navigationMain.visibility = View.VISIBLE
            } else {
                binding.navigationMain.visibility = View.GONE
            }
        }
    }
}