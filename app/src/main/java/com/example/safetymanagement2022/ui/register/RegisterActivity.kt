package com.example.safetymanagement2022.ui.register

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.safetymanagement2022.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    var passwordCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setRegisterButtonClickListener()
        setPasswordCheck(binding.editPw1, binding.editPw2)
    }

    private fun setRegisterButtonClickListener() {
        binding.btnRegister.setOnClickListener {
            val policyTermsCheck = binding.cbPolicyTerms.isChecked
            if (passwordCheck && policyTermsCheck) {
                sendRegisterInfo()
            }
            else if (!passwordCheck)
                Toast.makeText(applicationContext, "비밀번호를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext, "약관 동의에 체크해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendRegisterInfo() {
        val id = binding.editId.text
        val pw = binding.editPw2.text
        val companyName = binding.editCompanyName.text
        val name = binding.editName.text
        val admin = binding.rdoUser.isChecked
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