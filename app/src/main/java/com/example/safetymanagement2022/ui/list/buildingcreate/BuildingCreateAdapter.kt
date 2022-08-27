package com.example.safetymanagement2022.ui.list.buildingcreate

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemFloorPlanBinding
import com.example.safetymanagement2022.model.FloorPlanData

class BuildingCreateAdapter(val viewModel: BuildingCreateViewModel, val frag: BuildingCreate2Fragment): ListAdapter<FloorPlanData, BuildingCreateAdapter.BuildingCreateViewHolder>(
    ListBuildingDiffCallback()) {
    private lateinit var binding: ItemFloorPlanBinding
    var arrImgView = arrayListOf<ImageView>()

    // Adapter 값 리턴하기 위한 인터페이스 객체
    private lateinit var mCallback: SelectImageInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingCreateViewHolder {
        mCallback = frag as SelectImageInterface

        binding = ItemFloorPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingCreateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingCreateViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class BuildingCreateViewHolder(private val binding: ItemFloorPlanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(floor: FloorPlanData, position: Int) {
            binding.floor = floor
            binding.viewModel = viewModel
            binding.executePendingBindings()

            arrImgView.add(binding.ivFloorPlan)

            binding.ivFloorPlan.setOnClickListener {
                // 리턴할 값 넣기!!
                mCallback.onSelectedImage(position)
            }
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