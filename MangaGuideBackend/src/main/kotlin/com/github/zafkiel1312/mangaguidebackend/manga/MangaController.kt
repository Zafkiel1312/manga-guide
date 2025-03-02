package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaFromSourceDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
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

    @GetMapping("/sources")
    @ResponseStatus(HttpStatus.OK)
    fun getAllMangaSources(): List<String> =
        mangaService.getAllMangaSources()

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchMangaOnSource(@RequestParam sourceKey: String, @RequestParam searchString: String): List<SearchResultDto> =
        mangaService.searchMangaOnSource(sourceKey, searchString)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createMangaFromMangaSource(@RequestParam sourceKey: String, @RequestBody createMangaFromSourceDto: CreateMangaFromSourceDto) =
        mangaService.createMangaFromMangaSource(sourceKey, createMangaFromSourceDto)
}