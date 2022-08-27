package com.example.safetymanagement2022.ui.list.glass

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.USER_ID
import com.example.safetymanagement2022.databinding.FragmentListSmartglassBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.list.glasscreate.GlassCreateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSmartGlassFragment: BaseFragment<FragmentListSmartglassBinding>(R.layout.fragment_list_smartglass) {
    private val viewModel: ListSmartGlassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val admin = viewModel.listGlassResponse.value?.admin ?: 0
        binding.admin = admin
        binding.viewModel = viewModel

        viewModel.getListGlass(USER_ID)
        viewModel.listGlassResponse.observe(viewLifecycleOwner) { data ->
            binding.rvListSmartglass.adapter = ListSmartGlassAdapter(admin).apply {
                submitList(data.glassList)
            }
        }
        viewModel.openCreateGlassEvent.observe(viewLifecycleOwner) {
            openCreateGlass()
        }
    }

    private fun openCreateGlass() {
        val intent = Intent(context, GlassCreateActivity::class.java)
        startActivity(intent)
    }

}