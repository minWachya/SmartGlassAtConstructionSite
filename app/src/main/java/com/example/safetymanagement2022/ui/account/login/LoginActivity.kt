package com.example.safetymanagement2022.ui.account.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.safetymanagement2022.MainActivity
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.databinding.ActivityLoginBinding
import com.example.safetymanagement2022.ui.account.AccountViewModel
import com.example.safetymanagement2022.ui.base.BaseActivity
import com.example.safetymanagement2022.ui.account.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNextButtonListener()
        setRegisterButtonListener()

        viewModel.loginResponse.observe(this@LoginActivity) { response ->
            Toast.makeText(applicationContext, response.message, Toast.LENGTH_SHORT).show()
            if (response.message.contains("성공")) {
                // 메인 화면으로 이동
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setNextButtonListener() {
        binding.btnNext.setOnClickListener {
            postLogin()
        }
    }

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