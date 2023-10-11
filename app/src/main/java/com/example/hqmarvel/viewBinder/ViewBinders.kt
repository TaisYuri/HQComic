package com.example.hqmarvel.viewBinder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

fun ImageView.bindSrcUrl( url: String){
    Glide
        .with(this)
        .load(url)
        .centerInside()
        .into(this)
}

fun ImageCarousel.imageList(imageList: List<CarouselItem>?){
    imageList?.let {
        this.setData(it)
    }
}