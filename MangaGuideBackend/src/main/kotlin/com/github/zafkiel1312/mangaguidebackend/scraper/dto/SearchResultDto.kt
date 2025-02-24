package com.github.zafkiel1312.mangaguidebackend.scraper.dto

data class SearchResultDto(
    val name: String,
    val author: List<String>,
    val publisher: List<String>,
    val imageUrl: String,
    val detailsUrl: String,
)
