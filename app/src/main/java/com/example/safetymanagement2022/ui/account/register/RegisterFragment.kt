package com.example.safetymanagement2022.ui.account.register

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_MANAGER
import com.example.safetymanagement2022.common.KEY_USER
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.databinding.FragmentRegisterBinding
import com.example.safetymanagement2022.ui.account.AccountViewModel
import com.example.safetymanagement2022.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
    private val viewModel: AccountViewModel by viewModels()
    var passwordCheck = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setRegisterButtonClickListener()
        setPasswordCheck(binding.editPw1, binding.editPw2)

        viewModel.registerResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun setRegisterButtonClickListener() {
        binding.btnRegister.setOnClickListener {
            val policyTermsCheck = binding.cbPolicyTerms.isChecked
            if (passwordCheck && policyTermsCheck) {
                postRegister()
            }
            else if (!passwordCheck)
                Toast.makeText(context, "비밀번호를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "약관 동의에 체크해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun postRegister() {
        val id = binding.editId.text.toString()
        val pw = binding.editPw2.text.toString()
        val companyName = binding.editCompanyName.text.toString()
        val name = binding.editName.text.toString()
        val admin = if(binding.rdoUser.isChecked) KEY_USER else KEY_MANAGER
        val request = RegisterRequest(id, pw, name, companyName, admin)
        viewModel.postRegister(request)
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