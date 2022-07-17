package com.example.safetymanagement2022.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.safetymanagement2022.databinding.ItemSafetyIssueBinding
import com.example.safetymanagement2022.model.SafetyIssue

class HomeAdapter(val item: List<SafetyIssue>):  RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(), Filterable {
    private lateinit var binding: ItemSafetyIssueBinding

    private var unFilteredList = item
    private var filteredList = item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemSafetyIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = filteredList[position]
        holder.bind(item)
    }

    inner class HomeViewHolder(private val binding: ItemSafetyIssueBinding): RecyclerView.ViewHolder(binding.root) {
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
                        if (item.name == charString) filteringList.add(item)
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList

                Log.d("mmm list", filteredList.toString())
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<SafetyIssue>
                notifyDataSetChanged()
            }
        }
    }
}