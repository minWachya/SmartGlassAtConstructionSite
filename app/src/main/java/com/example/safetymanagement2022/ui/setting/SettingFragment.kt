package com.example.safetymanagement2022.ui.setting

import android.os.Bundle
import android.view.View
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.FragmentSettingBinding
import com.example.safetymanagement2022.ui.base.BaseFragment

class SettingFragment: BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

}