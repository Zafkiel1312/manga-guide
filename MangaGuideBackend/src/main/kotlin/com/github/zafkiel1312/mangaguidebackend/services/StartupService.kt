package com.github.zafkiel1312.mangaguidebackend.services

import com.github.zafkiel1312.mangaguidebackend.manga.MangaService
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaFromSourceDto
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class StartupService(
    private val mangaService: MangaService
) {

    @PostConstruct
    fun startup() {
        listOf<Long>(2, 269, 652, 275, 486, 87).forEach {
            mangaService.createMangaFromMangaSource("manga-passion", CreateMangaFromSourceDto(it.toString()))
        }
    }
}