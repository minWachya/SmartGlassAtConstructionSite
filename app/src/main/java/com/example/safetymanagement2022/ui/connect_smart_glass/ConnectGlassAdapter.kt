package com.example.safetymanagement2022.ui.connect_smart_glass

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.ItemDialogSmartGlassBinding
import com.example.safetymanagement2022.model.ConnectSmartGlass
import com.google.android.material.color.MaterialColors.getColor

class ConnectGlassAdapter: ListAdapter<ConnectSmartGlass, ConnectGlassAdapter.ConnectGlassViewHolder>(
    GlassDiffCallback()) {
    private lateinit var binding: ItemDialogSmartGlassBinding
    private var selectedSmartGlassId: String = ""

    private var prevView: TextView? = null
    private var currView: TextView? = null

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

                if(currView == null) {
                    prevView = binding.tvGlassName
                    currView = prevView
                    currView?.setBackgroundColor(Color.parseColor("#FFC589"))
                } else {
                    prevView?.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    currView = binding.tvGlassName
                    prevView = currView
                    currView?.setBackgroundColor(Color.parseColor("#FFC589"))
                }
            }
        }
    }

    fun getSmartGlassId(): String = selectedSmartGlassId
}

class GlassDiffCallback: DiffUtil.ItemCallback<ConnectSmartGlass>() {
    override fun areItemsTheSame(oldItem: ConnectSmartGlass, newItem: ConnectSmartGlass): Boolean {
        return oldItem.smartGlassId == newItem.smartGlassId
    }
    override fun areContentsTheSame(oldItem: ConnectSmartGlass, newItem: ConnectSmartGlass): Boolean {
        return oldItem == newItem
    }
}