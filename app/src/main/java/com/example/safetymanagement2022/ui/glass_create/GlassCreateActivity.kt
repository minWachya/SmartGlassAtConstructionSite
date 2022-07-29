package com.example.safetymanagement2022.ui.glass_create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.databinding.ActivityGlassCreateBinding

class GlassCreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlassCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }

}