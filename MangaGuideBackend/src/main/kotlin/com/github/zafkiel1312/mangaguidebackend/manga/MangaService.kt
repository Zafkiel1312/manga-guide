package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.MangaPassionClient
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.edition.EditionResponseDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.volume.VolumeResponseDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.params.EditionParams
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.params.VolumeParams
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.sources.scraper.ScraperService
import com.github.zafkiel1312.mangaguidebackend.sources.scraper.dto.ScraperSearchResultDto
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeService
import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MangaService(
    private val mangaRepository: MangaRepository,
    private val mangaMapper: MangaMapper,
    private val publisherService: PublisherService,
    private val volumeService: VolumeService,
    private val scraperService: ScraperService,
    private val mangaPassionClient: MangaPassionClient
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

    fun getVolumesOfMangaWithId(id: UUID): List<VolumeDto> =
        volumeService.getVolumesOfMangaWithId(id)

    fun searchMangaFromMangaPassion(searchString: String): List<SearchResultDto> {
        val editions = mutableListOf<EditionResponseDto>()
        var keepGoing = true
        var i = 1
        while (keepGoing) {
            val volume = mangaPassionClient.getEditions(
                EditionParams(
                    searchString,
                    i++,
                    30
                )
            )
            if (volume.isEmpty()) {
                keepGoing = false
                continue
            }
            editions.addAll(volume)
        }

        return editions.map {
            val authors = it.sources.flatMap { sources ->
                sources.contributors.map { contributors ->
                    contributors.contributor.name
                }
            }
            SearchResultDto(
                it.id,
                it.title,
                authors,
                it.cover
            )
        }
    }

    @Transactional
    fun createMangaFromMangaPassion(mangaPassionId: Long): UUID {
        val edition = mangaPassionClient.getEditionById(mangaPassionId)
            ?: throw EntityNotFoundException("Manga with id $mangaPassionId could not be found in manga-passion")

        val volumes = mutableListOf<VolumeResponseDto>()

        var keepGoing = true
        var i = 1
        while (keepGoing) {
            val volume = mangaPassionClient.getVolumesOfEdition(edition.id, VolumeParams(i++, 30))
            if (volume.isEmpty()) {
                keepGoing = false
                continue
            }
            volumes.addAll(volume)
        }

        val publisher = publisherService.getAllPublishers().first().let {
            publisherService.getPublisherById(it.id!!)
        }

        val author = edition.sources.flatMap { source ->
            source.contributors.map { contributors ->
                contributors.contributor.name
            }
        }.toSet().toList()

        val releaseDate = volumes.firstOrNull { it.number == 1 }?.date
        val releasedVolumes = volumes.filter {
            it.date?.before(Date()) ?: false
        }.size
        val japaneseVolumes = volumes.size

        val nextVolumeReleaseDate = volumes.firstOrNull {
            it.date?.after(Date()) ?: false
        }?.date

        val entity = MangaEntity(
            null,
            mangaPassionId,
            edition.title,
            edition.cover,
            author,
            publisher,
            releaseDate,
            releasedVolumes,
            0,
            japaneseVolumes,
            false,
            false,
            nextVolumeReleaseDate,
            mutableListOf()
        )

        return mangaRepository.save(entity).also {
            volumeService.createVolumesFromMangaPassion(it, volumes)
        }.id!!
    }

    fun searchMangaFromScraper(searchString: String): List<ScraperSearchResultDto> =
        scraperService.searchByString(searchString)

    @Transactional
    fun createMangaFromScraper(scraperUrl: String): UUID {
        val details = scraperService.getDetails(scraperUrl)

        val publisher = publisherService.getAllPublishers().first().let {
            publisherService.getPublisherById(it.id!!)
        }

        val entity = MangaEntity(
            null,
            0,
            details.title,
            details.imageUrl,
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