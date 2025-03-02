package com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper

import com.github.zafkiel1312.mangaguidebackend.sources.MangaSource
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import org.springframework.stereotype.Service

@Service
class IncompleteMangaGuideScraperService(
    private val imgScraperService: ImgScraperService
): MangaSource {
    override val sourceKey: String = "incomplete-manga-guide"

    override fun searchManga(searchString: String): List<SearchResultDto> =
        imgScraperService.searchByString(searchString)


    override fun getMangaDetails(sourceId: String): NewMangaDto {
        TODO("Not yet implemented")
    }
}