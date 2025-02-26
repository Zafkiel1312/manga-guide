package com.github.zafkiel1312.mangaguidebackend.publisher.dto

import java.util.*

data class PublisherDto(
    val id: UUID? = null,
    val name: String,
    val imageUrl: String,
    val mangaIds: List<UUID>
)
