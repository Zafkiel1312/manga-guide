package com.github.zafkiel1312.mangaguidebackend.sources.mangapassionscraper

import com.github.zafkiel1312.mangaguidebackend.sources.MangaSource
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewVolumeDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassionscraper.dto.VolumeDetailsDto
import org.springframework.stereotype.Service

@Service
class MangaPassionScraperService(
    private val scraperService: ScraperService
): MangaSource {
    override val sourceKey: String = "manga-passion-scraper"

    override fun searchManga(searchString: String): List<SearchResultDto> =
        scraperService.searchByString(searchString).map {
            SearchResultDto(
                it.detailsUrl,
                it.name,
                it.author,
                it.imageUrl
            )
        }

    override fun getMangaDetails(sourceId: String): NewMangaDto {
        val details = scraperService.getDetails(sourceId)

        return NewMangaDto(
            sourceKey,
            sourceId,
            details.title,
            details.imageUrl,
            details.author,
            "publisher",
            details.releaseDate,
            details.releasedVolumes,
            details.japaneseVolumes,
            false,
            false,
            details.nextVolumeReleaseDate,
            getDetailsFromVolumes(details.volumes)
        )
    }

    private fun getDetailsFromVolumes(volumes: List<VolumeDetailsDto>): List<NewVolumeDto> =
        volumes.map { volumeDetails ->
            NewVolumeDto(
                "", // ToDo get sourceUrl
                volumeDetails.number,
                volumeDetails.releaseDate,
                volumeDetails.released,
                volumeDetails.imageUrl
            )
        }

}