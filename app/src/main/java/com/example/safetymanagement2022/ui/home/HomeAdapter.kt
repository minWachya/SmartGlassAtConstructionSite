package com.example.safetymanagement2022.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemSafetyIssueBinding
import com.example.safetymanagement2022.model.SafetyIssue

class HomeAdapter: ListAdapter<SafetyIssue, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {
    private lateinit var binding: ItemSafetyIssueBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemSafetyIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: ItemSafetyIssueBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: SafetyIssue) {
            binding.safetyIssue = issue
            binding.executePendingBindings()
        }
    }
}

class HomeDiffCallback: DiffUtil.ItemCallback<SafetyIssue>() {
    override fun areItemsTheSame(oldItem: SafetyIssue, newItem: SafetyIssue): Boolean {
        return oldItem.rawDataId == newItem.rawDataId
    }
    override fun areContentsTheSame(oldItem: SafetyIssue, newItem: SafetyIssue): Boolean {
        return oldItem == newItem
    }
}