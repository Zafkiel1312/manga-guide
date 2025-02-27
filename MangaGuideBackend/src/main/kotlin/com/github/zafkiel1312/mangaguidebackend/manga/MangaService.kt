package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.MangaPassionService
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassionscraper.MangaPassionScraperService
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
    private val scraperService: MangaPassionScraperService,
    private val mangaPassionService: MangaPassionService
) {
//    @Transactional
//    fun createMange(createMangaDto: CreateMangaDto): UUID =
//        mangaMapper.convertToEntity(createMangaDto).let {
//            mangaRepository.save(it)
//        }.id!!

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

    fun searchMangaFromMangaPassion(searchString: String): List<SearchResultDto> =
        mangaPassionService.searchManga(searchString)

    @Transactional
    fun createMangaFromMangaSource(mangaDetails: NewMangaDto): UUID {
//        val publisher = publisherService.getAllPublishers().first().let {
//            publisherService.getPublisherById(it.id!!)
//        }

        return mangaRepository.save(
            mangaMapper.convertToEntity(mangaDetails)
//            MangaEntity(
//                null,
//                mangaDetails.sourceKey,
//                mangaDetails.sourceMangaId,
//                mangaDetails.title,
//                mangaDetails.imageUrl,
//                mangaDetails.author,
//                publisher,
//                mangaDetails.releaseDate,
//                mangaDetails.releasedVolumes,
//                0,
//                mangaDetails.japaneseVolumes,
//                mangaDetails.finished,
//                mangaDetails.japaneseFinished,
//                mangaDetails.nextVolumeReleaseDate,
//                mutableListOf()
//            )
        ).also {
            volumeService.createVolumesFromMangaSource(it, mangaDetails.volumes)
        }.id!!
    }

    @Transactional
    fun createMangaFromMangaPassion(mangaPassionId: Long): UUID =
        mangaPassionService.getMangaDetails(mangaPassionId.toString())
            .let(this::createMangaFromMangaSource)

    fun searchMangaFromScraper(searchString: String): List<SearchResultDto> =
        scraperService.searchManga(searchString)

    @Transactional
    fun createMangaFromScraper(scraperUrl: String): UUID =
        scraperService.getMangaDetails(scraperUrl)
            .let(this::createMangaFromMangaSource)

}