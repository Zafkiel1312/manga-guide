package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/manga")
class MangaController(
    private val mangaService: MangaService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createManga(@RequestBody createMangaDto: CreateMangaDto): UUID =
        mangaService.createMange(createMangaDto)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllMangas(): List<MangaDto> =
        mangaService.getAllMangas()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getMangaById(@PathVariable id: UUID): MangaDto =
        mangaService.getMangaDtoById(id)

    @GetMapping("/{id}/volumes")
    @ResponseStatus(HttpStatus.OK)
    fun getVolumesOfMangaWithId(@PathVariable id: UUID): List<VolumeDto> =
        mangaService.getVolumesOfMangaWithId(id)

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchMangaOnMangaPassion(@RequestParam searchString: String): List<SearchResultDto> =
        mangaService.searchMangaFromMangaPassion(searchString)

    @PostMapping("/{mangaPassionId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createMangaByMangaPassionId(@PathVariable mangaPassionId: Long) =
        mangaService.createMangaFromMangaPassion(mangaPassionId)
}