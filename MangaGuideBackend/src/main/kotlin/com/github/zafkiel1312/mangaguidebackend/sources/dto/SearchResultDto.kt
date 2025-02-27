package com.github.zafkiel1312.mangaguidebackend.sources.dto

data class SearchResultDto(
    val mangaSourceId: String,
    val title: String,
    val author: List<String>,
    val imageUrl: String,
)
