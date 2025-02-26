package com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.edition

import com.fasterxml.jackson.annotation.JsonProperty

data class EditionResponseDto(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("cover")
    val cover: String,
    @JsonProperty("sources")
    val sources: List<SourcesDto>,
    @JsonProperty("publishers")
    val publishers: List<PublisherDto>
)
