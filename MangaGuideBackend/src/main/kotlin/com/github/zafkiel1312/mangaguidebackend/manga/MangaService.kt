package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaFromSourceDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.MangaSource
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeService
import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MangaService(
    private val mangaRepository: MangaRepository,
    private val mangaMapper: MangaMapper,
    private val volumeService: VolumeService,
    private val mangaSources: List<MangaSource>
) {
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

    fun getAllMangaSources(): List<String> =
        mangaSources.map {
            it.sourceKey
        }

    fun searchMangaOnSource(sourceKey: String, searchString: String): List<SearchResultDto> =
        mangaSources.first {
            it.sourceKey == sourceKey
        }.searchManga(searchString)

    @Transactional
    fun createMangaFromMangaSource(sourceKey: String, createMangaFromSourceDto: CreateMangaFromSourceDto): UUID {
        val mangaDetails = mangaSources.first {
            it.sourceKey == sourceKey
        }.getMangaDetails(createMangaFromSourceDto.sourceMangaId)

        return mangaRepository.save(
            mangaMapper.convertToEntity(mangaDetails)
        ).also {
            volumeService.createVolumesFromMangaSource(it, mangaDetails.volumes)
        }.id!!
    }
}