package com.example.safetymanagement2022.ui.account.login

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
            if(checkPermission()) {
                Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                if (response.message.contains("성공")) {
                    // 메인 화면으로 이동
                    findNavController().navigate(R.id.action_frag_login_to_navigation_home)
                }
            } else {
                requestPermission()
                Toast.makeText(context, "권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
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
        if(checkPermission()) {
            binding.tvRegister.setOnClickListener {
                // 회원가입 화면으로 이동
                findNavController().navigate(R.id.action_frag_login_to_frag_register)
            }
        }
        else {
            requestPermission()
            Toast.makeText(context, "권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    // 권한 확인
    private fun checkPermission(): Boolean {
        // 문서 접근 권한 승인상태
        val permission = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    // 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 99)
    }

}