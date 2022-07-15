package com.example.safetymanagement2022.ui.building_create

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemFloorPlanBinding
import com.example.safetymanagement2022.model.FloorPlanData

class BuildingCreateAdapter(val viewModel: BuildingCreateViewModel): ListAdapter<FloorPlanData, BuildingCreateAdapter.BuildingCreateViewHolder>(ListBuildingDiffCallback()) {
    private lateinit var binding: ItemFloorPlanBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingCreateViewHolder {
        binding = ItemFloorPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingCreateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingCreateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BuildingCreateViewHolder(private val binding: ItemFloorPlanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(floor: FloorPlanData) {
            binding.floor = floor
            binding.viewModel = viewModel
            binding.executePendingBindings()

//            binding.ivFloorPlan.setOnClickListener {
//                Log.d("mmm", floor.label)
//            }
        }
    }
}

class ListBuildingDiffCallback: DiffUtil.ItemCallback<FloorPlanData>() {
    override fun areItemsTheSame(oldItem: FloorPlanData, newItem: FloorPlanData): Boolean {
        return oldItem.image == newItem.image
    }
    override fun areContentsTheSame(oldItem: FloorPlanData, newItem: FloorPlanData): Boolean {
        return oldItem == newItem
    }
}