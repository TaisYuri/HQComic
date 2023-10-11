package com.example.hqmarvel.presentation.listagent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.databinding.ItemAgentBinding
import com.example.hqmarvel.presentation.listagent.adapter.viewholder.AgentListViewHolder

class AgentListAdapter(private val listener: (AgentData) -> Unit) :
    RecyclerView.Adapter<AgentListViewHolder>() {

    private var valuesList: List<AgentData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentListViewHolder {
        val view = ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentListViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return valuesList.size
    }

    override fun onBindViewHolder(holder: AgentListViewHolder, position: Int) {
        val item = valuesList[position]
        holder.bind(item)
    }

    fun updateData(listAgent: List<AgentData>) {
        valuesList = listAgent
        notifyDataSetChanged()
    }

}