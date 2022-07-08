package com.example.safetymanagement2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.safetymanagement2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.navigationMain.itemIconTintList = null    // Theme 의 color 로 아이콘 색 표시 X, drawable 그대로 사용

        // FragmentContainerView 의 NavController 가져와서 bottomNavigation 에 전달
        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let { binding.navigationMain.setupWithNavController(it) }
    }
}