package com.github.zafkiel1312.mangaguidebackend.services

import com.github.zafkiel1312.mangaguidebackend.manga.MangaService
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.util.*

@Service
class StartupService(
    private val mangaService: MangaService,
    private val publisherService: PublisherService,
    private val volumeService: VolumeService
) {

    @PostConstruct
    fun startup() {
        val publisherId = publisherService.createPublisher(
            CreatePublisherDto(
                "Generic Publisher",
                "https://www.thelostdungeon.de/wp-content/uploads/2015/10/kaze-manga-logo-klein.jpg"
            )
        )

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
    }
}