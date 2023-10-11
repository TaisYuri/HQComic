package com.example.hqmarvel.data.model.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@Entity
@JsonClass(generateAdapter = true)
class ComicData() {

    @PrimaryKey
    var id: Int? = null
    var title: String? = null
    var description: String? = null

    @Ignore
    var textObjects: List<TextObjects>? = null

    @Embedded
    var thumbnail: ImageData? = null

    @Ignore
    var images: List<ImageData>? = null


    @Ignore
    //Construtor secundário
    constructor(
        id: Int?,
        title: String?,
        description: String?,
        textObjects: List<TextObjects>?,
        thumbnail: ImageData?,
        images: List<ImageData>?
    ) : this() {
        this.id = id
        this.title = title
        this.description = description
        this.textObjects = textObjects
        this.thumbnail = thumbnail
        this.images = images
    }


    fun getContent(): String {
        val desc = description // utilizado para garantir que o valor não mudará na verificação abaixo.
        val text = textObjects
        return when {
            desc?.isNotEmpty() == true -> desc
            text?.isNotEmpty() == true -> text[0].text ?: "Conteúdo não disponivel"
            else -> "Conteúdo não disponivel"
        }
    }

    fun getIdString(): String {
        return id?.toString() ?: ""
    }

    fun getImageUrl() = thumbnail?.getFullImagePath()

    fun getCarouselImages(): List<CarouselItem>? = images?.map {
        CarouselItem(imageUrl = it.getFullImagePath())
    }

}