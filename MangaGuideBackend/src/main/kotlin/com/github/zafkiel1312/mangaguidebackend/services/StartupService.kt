package com.github.zafkiel1312.mangaguidebackend.services

import com.github.zafkiel1312.mangaguidebackend.manga.MangaService
import com.github.zafkiel1312.mangaguidebackend.mangapassion.MangaPassionClient
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class StartupService(
    private val mangaService: MangaService,
    private val publisherService: PublisherService,
    private val volumeService: VolumeService,
    private val mangaPassionClient: MangaPassionClient
) {

    @PostConstruct
    fun startup() {
        val publisherId = publisherService.createPublisher(
            CreatePublisherDto(
                "Generic Publisher",
                "https://www.thelostdungeon.de/wp-content/uploads/2015/10/kaze-manga-logo-klein.jpg"
            )
        )

        listOf<Long>(2, 269, 652, 275, 486, 87).forEach(mangaService::createMangaFromMangaPassion)

//        listOf(
//            "https://www.manga-passion.de/editions/2/komi-cant-communicate",
//            "https://www.manga-passion.de/editions/534/shy",
//            "https://www.manga-passion.de/editions/652/tonikawa-fly-me-to-the-moon",
//            "https://www.manga-passion.de/editions/269/chainsaw-man",
//            "https://www.manga-passion.de/editions/275/more-than-a-doll",
//            "https://www.manga-passion.de/editions/364/spy-x-family",
//            "https://www.manga-passion.de/editions/907/spice-wolf",
//            "https://www.manga-passion.de/editions/1452/call-of-the-night",
//            "https://www.manga-passion.de/editions/796/die-braut-des-magiers",
//            "https://www.manga-passion.de/editions/501/monstermaessig-verknallt",
//        ).forEach(mangaService::createMangaFromScraper)
/*
        mangaService.createMange(
            CreateMangaDto(
                "The Younger Sister Of The Male Lead I Defeated Is Ridiculously Wealthy?!",
                "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
                listOf("Eiichiro Oda"),
                publisherId,
                Date(),
                102,
                12,
                110,
                false,
                false,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Accel World",
                "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
                listOf("Hiroyuki Aigamo"),
                publisherId,
                Date(),
                8,
                8,
                8,
                true,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Chainsaw Man",
                "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
                listOf("Tatsuki Fujimoto"),
                publisherId,
                Date(),
                16,
                16,
                20,
                false,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "One Piece",
                "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
                listOf("Eiichiro Oda"),
                publisherId,
                Date(),
                102,
                12,
                110,
                false,
                false,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Accel World",
                "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
                listOf("Hiroyuki Aigamo"),
                publisherId,
                Date(),
                8,
                8,
                8,
                true,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Chainsaw Man",
                "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
                listOf("Tatsuki Fujimoto"),
                publisherId,
                Date(),
                16,
                16,
                20,
                false,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "One Piece",
                "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
                listOf("Eiichiro Oda"),
                publisherId,
                Date(),
                102,
                12,
                110,
                false,
                false,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Accel World",
                "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
                listOf("Hiroyuki Aigamo"),
                publisherId,
                Date(),
                8,
                8,
                8,
                true,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Chainsaw Man",
                "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
                listOf("Tatsuki Fujimoto"),
                publisherId,
                Date(),
                16,
                16,
                20,
                false,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "One Piece",
                "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
                listOf("Eiichiro Oda"),
                publisherId,
                Date(),
                102,
                12,
                110,
                false,
                false,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Accel World",
                "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
                listOf("Hiroyuki Aigamo"),
                publisherId,
                Date(),
                8,
                8,
                8,
                true,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Chainsaw Man",
                "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
                listOf("Tatsuki Fujimoto"),
                publisherId,
                Date(),
                16,
                16,
                20,
                false,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "One Piece",
                "https://mangaguide.de/bilder/frontcover_gross/162.jpg",
                listOf("Eiichiro Oda"),
                publisherId,
                Date(),
                102,
                12,
                110,
                false,
                false,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Accel World",
                "https://mangaguide.de/bilder/frontcover_gross/11034.jpg",
                listOf("Hiroyuki Aigamo"),
                publisherId,
                Date(),
                8,
                8,
                8,
                true,
                true,
                Date()
            )
        )

        mangaService.createMange(
            CreateMangaDto(
                "Chainsaw Man",
                "https://mangaguide.de/bilder/frontcover_gross/20026.jpg",
                listOf("Tatsuki Fujimoto"),
                publisherId,
                Date(),
                16,
                16,
                20,
                false,
                true,
                Date()
            )
        )
        */
    }
}