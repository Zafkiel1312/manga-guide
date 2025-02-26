package com.github.zafkiel1312.mangaguidebackend.sources.scraper.dto

data class ScraperSearchResultDto(
    val name: String,
    val author: List<String>,
    val publisher: List<String>,
    val imageUrl: String,
    val detailsUrl: String,
)
