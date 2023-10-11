package com.example.hqmarvel.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hqmarvel.data.model.response.ComicData
import com.example.hqmarvel.data.model.response.ImageData
import com.example.hqmarvel.data.model.response.TextObjects


//CLASSE RELAÇÃO ENTRE COMIC + IMAGES + TEXTOBJECTS
data class ComicWithAllProperties (
    @Embedded var comic: ComicData,

    @Relation(
        entity = ImageData::class,
        parentColumn = "id",
        entityColumn = "comicId"
    ) var images: List<ImageData>,

    @Relation(
        entity = TextObjects::class,
        parentColumn = "id",
        entityColumn = "comicId"
    ) var textObjects: List<TextObjects>,

)