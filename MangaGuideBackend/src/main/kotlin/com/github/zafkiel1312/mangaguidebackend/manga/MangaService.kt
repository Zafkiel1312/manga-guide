package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MangaService(
    private val mangaRepository: MangaRepository,
    private val mangaMapper: MangaMapper,
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
}