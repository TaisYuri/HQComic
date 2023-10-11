package com.example.hqmarvel.data.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgentResponse(
    val code: Int?,
    val status: String?,
    val data: AgentContainer?
)

@JsonClass(generateAdapter = true)
data class AgentContainer(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count : Int?,
    val results: List<AgentData>?,
)


@JsonClass(generateAdapter = true)
data class AgentData(
    val name: String?,
    val description: String?,
    val thumbnail: ImageData?,
){
    fun getImageUrl() = thumbnail?.getFullImagePath()
}


