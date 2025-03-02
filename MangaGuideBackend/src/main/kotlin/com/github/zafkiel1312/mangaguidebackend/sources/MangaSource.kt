package com.github.zafkiel1312.mangaguidebackend.sources

import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto

interface MangaSource {
    val sourceKey: String

    fun searchManga(searchString: String): List<SearchResultDto>

    fun getMangaDetails(sourceId: String): NewMangaDto
}