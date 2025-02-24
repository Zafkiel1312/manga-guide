package com.github.zafkiel1312.mangaguidebackend.mangapassion.dto.edition

import com.fasterxml.jackson.annotation.JsonProperty

data class ContributorOuterDto(
    @JsonProperty("name")
    val id: Long,
    @JsonProperty("contributor")
    val contributor: ContributorInnerDto,
    @JsonProperty("role")
    val role: Long
)
