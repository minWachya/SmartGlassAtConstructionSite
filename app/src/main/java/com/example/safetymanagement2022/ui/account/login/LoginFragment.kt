package com.example.safetymanagement2022.ui.account.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_TYPE
import com.example.safetymanagement2022.common.KEY_TYPE_ACCOUNT
import com.example.safetymanagement2022.common.KEY_TYPE_SETTING
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.databinding.FragmentLoginBinding
import com.example.safetymanagement2022.ui.account.AccountViewModel
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: AccountViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()
    private lateinit var type: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        type = try{
            requireArguments().getString(KEY_TYPE)?: KEY_TYPE_ACCOUNT
        } catch(e: IllegalStateException){
            KEY_TYPE_ACCOUNT
        }

        setTitle()
        setNextButtonListener()
        setRegisterButtonListener()

        viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            if (response.message.contains("성공")) {
                // 메인 화면으로 이동
                findNavController().navigate(R.id.action_frag_login_to_navigation_home)
            }
        }
        settingViewModel.changePwResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            if (response.message.contains("성공")) {
                // 메인 화면으로 이동
                findNavController().navigate(R.id.action_frag_login_to_navigation_home)
            }
        }
    }

    private fun setTitle() {
        if(type == KEY_TYPE_SETTING) {
            binding.tvLogin.text = "비밀번호 변경"
        }
    }

    private fun setNextButtonListener() {
        binding.btnNext.setOnClickListener {
            val id = binding.editId.text.toString()
            val pw = binding.editPw.text.toString()
            if(type == KEY_TYPE_ACCOUNT)
                postAccountLogin(id, pw)
            else postSettingChangePw(id, pw)
        }
    }

    private fun postAccountLogin(id: String, pw: String) {
        val request = LoginRequest(id, pw)
        viewModel.postLogin(request)
    }
    private fun postSettingChangePw(id: String, pw: String) {
        val request = LoginRequest(id, pw)
        settingViewModel.putChangePw(request)
    }

    private fun setRegisterButtonListener() {
        binding.tvRegister.setOnClickListener {
            // 회원가입 화면으로 이동
            findNavController().navigate(R.id.action_frag_login_to_frag_register)
        }
    }
}