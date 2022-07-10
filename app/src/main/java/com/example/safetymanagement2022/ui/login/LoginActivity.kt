package com.example.safetymanagement2022.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.MainActivity
import com.example.safetymanagement2022.databinding.ActivityLoginBinding
import com.example.safetymanagement2022.ui.register.RegisterActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setNextButtonListener()
        setRegisterButtonListener()
    }

    private fun setNextButtonListener() {
        binding.btnNext.setOnClickListener {
            // 메인 화면으로 이동
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setRegisterButtonListener() {
        binding.tvRegister.setOnClickListener {
            // 회원가입 화면으로 이동
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}