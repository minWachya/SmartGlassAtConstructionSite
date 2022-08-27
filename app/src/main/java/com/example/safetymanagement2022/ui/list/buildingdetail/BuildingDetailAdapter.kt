package com.example.safetymanagement2022.ui.list.buildingdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.data.remote.model.response.SafetyIssue
import com.example.safetymanagement2022.databinding.ItemSafetyIssueBinding

class BuildingDetailAdapter(val item: List<SafetyIssue>):  RecyclerView.Adapter<BuildingDetailAdapter.DetailViewHolder>(), Filterable {
    private lateinit var binding: ItemSafetyIssueBinding

    private var unFilteredList = item
    private var filteredList = item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        binding = ItemSafetyIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item = filteredList[position]
        holder.bind(item)
    }

    inner class DetailViewHolder(private val binding: ItemSafetyIssueBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: SafetyIssue) {
            binding.safetyIssue = issue
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                filteredList = if (charString.isEmpty()) {
                    unFilteredList
                } else {
                    val filteringList = arrayListOf<SafetyIssue>()
                    for (item in unFilteredList)
                        if (item.floor == charString) filteringList.add(item)
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<SafetyIssue>
                notifyDataSetChanged()
            }
        }
    }
}