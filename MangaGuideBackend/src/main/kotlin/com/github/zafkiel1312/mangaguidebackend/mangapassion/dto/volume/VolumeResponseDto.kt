package com.github.zafkiel1312.mangaguidebackend.mangapassion.dto.volume

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class VolumeResponseDto(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("number")
    val number: Int,
    @JsonProperty("date")
    val date: Date?,
    @JsonProperty("cover")
    val cover: String,
    @JsonProperty("type")
    val type: Int?,
    @JsonProperty("specialType")
    val specialType: Int?,
)
