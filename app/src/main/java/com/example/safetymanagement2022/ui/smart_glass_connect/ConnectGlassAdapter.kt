package com.example.safetymanagement2022.ui.smart_glass_connect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemDialogSmartGlassBinding
import com.example.safetymanagement2022.model.ConnectSmartGlass

class ConnectGlassAdapter: ListAdapter<ConnectSmartGlass, ConnectGlassAdapter.ConnectGlassViewHolder>(ListBuildingDiffCallback()) {
    private lateinit var binding: ItemDialogSmartGlassBinding
    var selectedSmartGlassId: String? = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectGlassViewHolder {
        binding = ItemDialogSmartGlassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConnectGlassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConnectGlassViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ConnectGlassViewHolder(private val binding: ItemDialogSmartGlassBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(glass: ConnectSmartGlass) {
            binding.glass = glass
            binding.executePendingBindings()

            binding.tvGlassName.setOnClickListener {
                selectedSmartGlassId = glass.smartGlassId
            }
        }
    }

    fun getSmartGlassId(): String? = selectedSmartGlassId
}

class ListBuildingDiffCallback: DiffUtil.ItemCallback<ConnectSmartGlass>() {
    override fun areItemsTheSame(oldItem: ConnectSmartGlass, newItem: ConnectSmartGlass): Boolean {
        return oldItem.smartGlassId == newItem.smartGlassId
    }
    override fun areContentsTheSame(oldItem: ConnectSmartGlass, newItem: ConnectSmartGlass): Boolean {
        return oldItem == newItem
    }
}