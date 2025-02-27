package com.github.zafkiel1312.mangaguidebackend.sources.mangapassion

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.sources.MangaSource
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewVolumeDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.edition.EditionResponseDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.dto.volume.VolumeResponseDto
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.params.EditionParams
import com.github.zafkiel1312.mangaguidebackend.sources.mangapassion.params.VolumeParams
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class MangaPassionService(
    private val mangaPassionClient: MangaPassionClient
): MangaSource {
    override val sourceKey: String = "manga-passion"

    override fun searchManga(searchString: String): List<SearchResultDto> {
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
                it.id.toString(),
                it.title,
                authors,
                it.cover
            )
        }
    }

    override fun getMangaDetails(sourceId: String): NewMangaDto {
        val edition = mangaPassionClient.getEditionById(sourceId.toLong())
            ?: throw EntityNotFoundException("Manga with id $sourceKey could not be found in manga-passion")

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

        return NewMangaDto(
            sourceKey,
            sourceId,
            edition.title,
            edition.cover,
            author,
            "publisher", // ToDo add a real publisher
            releaseDate,
            releasedVolumes,
            japaneseVolumes,
            false,
            false,
            nextVolumeReleaseDate,
            getDetailsFromVolumes(volumes)
        )
    }

    private fun getDetailsFromVolumes(volumes: List<VolumeResponseDto>): List<NewVolumeDto> {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val invalidDateString = "2999-01-01"
        val invalidDate = formatter.parse(invalidDateString)

        val acceptedTypes = listOf(null, 0)

        return volumes.mapNotNull {

            if (!(acceptedTypes.contains(it.type) && acceptedTypes.contains(it.specialType))) {
                return@mapNotNull null
            }
            val releaseDate = if (it.date?.before(invalidDate) == true) {
                it.date
            } else {
                null
            }

            NewVolumeDto(
                it.id.toString(),
                it.number,
                releaseDate,
                it.date?.before(Date()) ?: false,
                it.cover
            )
        }
    }
}