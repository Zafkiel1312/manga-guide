package com.github.zafkiel1312.mangaguidebackend.mangapassion.dto.edition

import com.fasterxml.jackson.annotation.JsonProperty

data class PublisherDto(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("name")
    val name: String,
)
