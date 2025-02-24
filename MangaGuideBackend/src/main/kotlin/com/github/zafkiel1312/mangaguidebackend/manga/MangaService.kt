package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.scraper.ScraperService
import com.github.zafkiel1312.mangaguidebackend.scraper.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MangaService(
    private val mangaRepository: MangaRepository,
    private val mangaMapper: MangaMapper,
    private val publisherService: PublisherService,
    private val scraperService: ScraperService,
    private val volumeService: VolumeService
) {
    @Transactional
    fun createMange(createMangaDto: CreateMangaDto): UUID =
        mangaMapper.convertToEntity(createMangaDto).let {
            mangaRepository.save(it)
        }.id!!

    fun getAllMangas(): List<MangaDto> =
        mangaRepository.findAll()
            .map(mangaMapper::convertToDto)

    fun getMangaDtoById(id: UUID): MangaDto =
        getMangaById(id).let(mangaMapper::convertToDto)

    fun getMangaById(id: UUID): MangaEntity =
        mangaRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Manga with id $id could not be found")
        }

    fun searchMangaFromScraper(searchString: String): List<SearchResultDto> =
        scraperService.searchByString(searchString)

    @Transactional
    fun createMangaFromScraper(scraperUrl: String): UUID {
        val details = scraperService.getDetails(scraperUrl)

        val publisher = publisherService.getAllPublishers().first().let{
            publisherService.getPublisherById(it.id!!)
        }

        val entity = MangaEntity(
            null,
            details.title,
            details.pictureUrl,
            details.author,
            publisher,
            details.releaseDate,
            details.releasedVolumes,
            0,
            details.japaneseVolumes,
            false,
            false,
            details.nextVolumeReleaseDate,
            mutableListOf()
        )

        return mangaRepository.save(entity).also { manga ->
            details.volumes.forEach {
                volumeService.createVolumeFromScraper(manga, it)
            }
        }.id!!
    }
}