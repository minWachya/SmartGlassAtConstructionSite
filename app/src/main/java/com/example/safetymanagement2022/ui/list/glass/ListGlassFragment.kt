package com.example.safetymanagement2022.ui.list.glass

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.FragmentListGlassBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListGlassFragment: BaseFragment<FragmentListGlassBinding>(R.layout.fragment_list_glass) {
    private val viewModel: ListGlassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getListGlass(viewModel.getUserId())
        setOberverListGlassResponse()
        setObserverOpenCreateGlassEvent()
    }

    private fun setOberverListGlassResponse() {
        viewModel.listGlassResponse.observe(viewLifecycleOwner) { data ->
            binding.rvListSmartglass.adapter = ListSmartGlassAdapter(data.admin).apply {
                submitList(data.glassList)
            }
            binding.admin = data.admin
            binding.viewModel = viewModel
        }
    }

    private fun setObserverOpenCreateGlassEvent() {
        viewModel.openCreateGlassEvent.observe(viewLifecycleOwner, EventObserver {
            openCreateGlass()
        })
    }

    private fun openCreateGlass() {
        findNavController().navigate(R.id.action_navigation_list_to_frag_glass_create)
    }

}