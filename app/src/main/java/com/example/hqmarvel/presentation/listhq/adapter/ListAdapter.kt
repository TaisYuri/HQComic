package com.example.hqmarvel.presentation.listhq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hqmarvel.databinding.ItemHqListBinding
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.presentation.listhq.adapter.viewholder.ListViewHolder


interface ItemListener{
    fun onItemSelected(position: Int)
}

class ListAdapter(private val listener: ItemListener): RecyclerView.Adapter<ListViewHolder>() {

    private var values: List<ComicData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemHqListBinding.inflate(LayoutInflater.from(parent.context), parent, false))    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = values[position]

        holder.bindItem(item)

        holder.buttonClicked.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    fun updateData(list: List<ComicData>){
        values = list
        notifyDataSetChanged()
    }
}