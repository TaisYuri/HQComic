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
data class TextObjects(

    @PrimaryKey(autoGenerate = true)
    val textObjectId: Int?,

    val type: String?,

    val language: String?,

    val text: String?,

    @ColumnInfo(index = true)
    var comicId: Int?
)
