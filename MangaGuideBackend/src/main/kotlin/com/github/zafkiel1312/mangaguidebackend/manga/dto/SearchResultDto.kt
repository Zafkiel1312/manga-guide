package com.github.zafkiel1312.mangaguidebackend.manga.dto

data class SearchResultDto(
    val mangaPassionId: Long,
    val title: String,
    val author: List<String>,
    val imageUrl: String,
)
