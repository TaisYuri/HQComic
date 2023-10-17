package com.example.hqmarvel.presentation.listhq.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.hqmarvel.databinding.ItemHqListBinding
import com.example.hqmarvel.viewBinder.bindSrcUrl
import com.example.hqmarvel.data.model.response.ComicData

class ListViewHolder(private val binding: ItemHqListBinding): RecyclerView.ViewHolder(binding.root) {

    val view = binding.root
    val buttonClicked = binding.button

    fun bindItem(item: ComicData){
        binding.title.text = item.title
        binding.content.text = item.getContent()
        binding.imageView.bindSrcUrl(item.getImageUrl() ?: "Sem imagem")

    }
}