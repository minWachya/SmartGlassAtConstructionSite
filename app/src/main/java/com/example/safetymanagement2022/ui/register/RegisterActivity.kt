package com.example.safetymanagement2022.ui.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}