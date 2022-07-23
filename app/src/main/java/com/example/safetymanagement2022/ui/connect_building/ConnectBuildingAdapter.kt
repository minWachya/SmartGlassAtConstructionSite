package com.example.safetymanagement2022.ui.connect_building

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemDialogBuildingBinding
import com.example.safetymanagement2022.model.ConnectBuilding

class ConnectBuildingAdapter: ListAdapter<ConnectBuilding, ConnectBuildingAdapter.ConnectBuildingViewHolder>(
    ListBuildingDiffCallback()) {
    private lateinit var binding: ItemDialogBuildingBinding
    private var selectedBuildingId: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectBuildingViewHolder {
        binding = ItemDialogBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConnectBuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConnectBuildingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ConnectBuildingViewHolder(private val binding: ItemDialogBuildingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(building: ConnectBuilding) {
            binding.building = building
            binding.executePendingBindings()

            binding.tvBuildingName.setOnClickListener {
                selectedBuildingId = building.buildingId
            }
        }
    }

    fun getBuildingId(): String = selectedBuildingId
}

class ListBuildingDiffCallback: DiffUtil.ItemCallback<ConnectBuilding>() {
    override fun areItemsTheSame(oldItem: ConnectBuilding, newItem: ConnectBuilding): Boolean {
        return oldItem.buildingId == newItem.buildingId
    }
    override fun areContentsTheSame(oldItem: ConnectBuilding, newItem: ConnectBuilding): Boolean {
        return oldItem == newItem
    }
}