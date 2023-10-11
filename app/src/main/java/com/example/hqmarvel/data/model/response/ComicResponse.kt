package com.example.hqmarvel.data.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicResponse(
    val code: Int?,
    val status: String?,
    val data: ComicContainer?
)
