package com.example.hqmarvel.presentation.listagent.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.databinding.ItemAgentBinding
import com.example.hqmarvel.viewBinder.bindSrcUrl

class AgentListViewHolder(private val binding: ItemAgentBinding, private val listener: (AgentData) -> Unit): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AgentData){
        with(binding){
            name.text = item.name
            description.text = item.description
            banner.bindSrcUrl(item.getImageUrl() ?: "" )
            root.setOnClickListener {
                listener.invoke(item)
            }
        }
    }
}