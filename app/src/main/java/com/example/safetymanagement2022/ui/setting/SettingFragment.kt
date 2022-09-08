package com.example.safetymanagement2022.ui.setting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.FragmentSettingBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment: BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setClickListener()
        setLogOutResponse()
    }

    private fun setClickListener() {
        binding.tvLogout.setOnClickListener {
            val id = viewModel.getUserId()
            viewModel.getLogout(id)
        }
    }

    private fun setLogOutResponse() {
        viewModel.logoutResponse.observe(viewLifecycleOwner) { response ->
            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
            if (response.message.contains("되었")) {
                // 로그인인 화면으로 이동
                findNavController().navigate(R.id.action_navigation_setting_to_frag_login)
            }
        }
    }

}