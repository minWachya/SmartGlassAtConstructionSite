package com.example.safetymanagement2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safetymanagement2022.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}