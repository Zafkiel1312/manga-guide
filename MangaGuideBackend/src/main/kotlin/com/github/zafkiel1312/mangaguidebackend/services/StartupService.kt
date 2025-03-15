package com.github.zafkiel1312.mangaguidebackend.services

import com.github.zafkiel1312.mangaguidebackend.manga.MangaService
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper.IncompleteMangaGuideScraperService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class StartupService(
    private val mangaService: MangaService,
    private val imgScraperService: IncompleteMangaGuideScraperService,
    private val publisherService: PublisherService
) {

    @PostConstruct
    fun startup() {
        /*listOf<Long>(2, 269, 652, 275, 486, 87).forEach {
            mangaService.createMangaFromMangaSource("manga-passion", CreateMangaFromSourceDto(it.toString()))
        }*/

        publisherService.createPublisher(
            CreatePublisherDto(
                "test",
                "a"
            )
        )

        imgScraperService.searchManga("kom")
        imgScraperService.getMangaDetails("https://mangaguide.de/index.php?include=5&manga_id=3098")
    }
}