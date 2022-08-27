package com.example.safetymanagement2022.ui.register

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_MANAGER
import com.example.safetymanagement2022.common.KEY_USER
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.databinding.ActivityRegisterBinding
import com.example.safetymanagement2022.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity: BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    var passwordCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRegisterButtonClickListener()
        setPasswordCheck(binding.editPw1, binding.editPw2)

//        viewModel.registerResponse.observe(this@RegisterActivity) { response ->
//            Log.d("mmm register", response.message)
//        }
    }

    private fun setRegisterButtonClickListener() {
        binding.btnRegister.setOnClickListener {
            val policyTermsCheck = binding.cbPolicyTerms.isChecked
            if (passwordCheck && policyTermsCheck) {
//                postRegister()
            }
            else if (!passwordCheck)
                Toast.makeText(applicationContext, "비밀번호를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "약관 동의에 체크해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun postRegister() {
        val id = binding.editId.text.toString()
        val pw = binding.editPw2.text.toString()
        val companyName = binding.editCompanyName.text.toString()
        val name = binding.editName.text.toString()
        val admin = if(binding.rdoUser.isChecked) KEY_USER else KEY_MANAGER
        val request = RegisterRequest(id, pw, name, companyName, admin)
//        viewModel.postRegister(request)
    }

    private fun setPasswordCheck(pw1: EditText, pw2: EditText) {
        pw1.doOnTextChanged { _, _, _, _ ->
            val password1 = pw1.text.toString()
            val password2 = pw2.text.toString()

            // 비밀번호 6자리인지 확인
            if (!isPasswordValid(password1)) pw1.error = "비민번호는 6지리 이상이어야 합니다."
            else pw1.error = null

            // 비밀번호 정확히 입력했는지 확인
            if(password1 == password2) {
                passwordCheck = true
                pw2.error = null
            }
            else {
                passwordCheck = false
                pw2.error = "비밀번호가 다릅니다."
            }
        }
        pw2.doOnTextChanged { _, _, _, _ ->
            val password1 = pw1.text.toString()
            val password2 = pw2.text.toString()

            // 비밀번호 정확히 입력했는지 확인
            if(password1 == password2) {
                passwordCheck = true
                pw2.error = null
            }
            else {
                passwordCheck = false
                pw2.error = "비밀번호가 다릅니다."
            }
        }
    }

    // 비밀번호 6자리 이상인지 확인
    private fun isPasswordValid(password : String) : Boolean {
        return password.length >= 6
    }
}