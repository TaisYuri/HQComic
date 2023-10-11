package com.example.hqmarvel.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ComicData::class,
            parentColumns = ["id"],
            childColumns = ["comicId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@JsonClass(generateAdapter = true)
data class ImageData(

    @PrimaryKey(autoGenerate = true)
    val imageId: Int?,

    val path: String?,

    val extension: String?,

    @ColumnInfo(index = true)
    var comicId: Int?
){
    fun getFullImagePath(): String{
        val pathHttps = path?.replace("http", "https")
        return "$pathHttps.$extension"
    }
}
