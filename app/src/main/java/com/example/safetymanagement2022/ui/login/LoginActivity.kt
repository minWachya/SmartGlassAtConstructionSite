package com.example.safetymanagement2022.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.MainActivity
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.databinding.ActivityLoginBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory
import com.example.safetymanagement2022.ui.register.RegisterActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { MyViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setNextButtonListener()
        setRegisterButtonListener()

        viewModel.loginResponse.observe(this@LoginActivity) { response ->
            Log.d("mmm login", response.message)
        }
    }

    private fun setNextButtonListener() {
        binding.btnNext.setOnClickListener {
//            postLogin()
            // 메인 화면으로 이동
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 데이터 요청
    private fun postLogin() {
        val id = binding.editId.text.toString()
        val pw = binding.editPw.text.toString()
        val request = LoginRequest(id, pw)
        viewModel.postLogin(request)
    }

    private fun setRegisterButtonListener() {
        binding.tvRegister.setOnClickListener {
            // 회원가입 화면으로 이동
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}