package com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.edition

import com.fasterxml.jackson.annotation.JsonProperty

data class SourcesDto(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("contributors")
    val contributors: List<ContributorOuterDto>
)
