package com.example.safetymanagement2022.ui.list_building

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemListBuildingBinding
import com.example.safetymanagement2022.model.Building

class ListBuildingAdapter(val viewModel: ListBuildingViewModel): ListAdapter<Building, ListBuildingAdapter.ListBuildingViewHolder>(ListBuildingDiffCallback()) {
    private lateinit var binding: ItemListBuildingBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBuildingViewHolder {
        binding = ItemListBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListBuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListBuildingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ListBuildingViewHolder(private val binding: ItemListBuildingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(building: Building) {
            binding.building = building
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class ListBuildingDiffCallback: DiffUtil.ItemCallback<Building>() {
    override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem.buildingId == newItem.buildingId
    }
    override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
        return oldItem == newItem
    }
}