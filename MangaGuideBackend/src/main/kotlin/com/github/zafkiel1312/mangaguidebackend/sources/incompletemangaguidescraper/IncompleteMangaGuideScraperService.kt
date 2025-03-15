package com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper

import com.github.zafkiel1312.mangaguidebackend.sources.MangaSource
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewMangaDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewVolumeDto
import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper.dto.VolumeInformation
import com.github.zafkiel1312.mangaguidebackend.sources.util.DateUtil
import com.github.zafkiel1312.mangaguidebackend.sources.util.RequestUtil
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Date

@Service
class IncompleteMangaGuideScraperService : MangaSource {
    private val requestUtil = RequestUtil()
    private val dateUtil = DateUtil()
    private val logger = LoggerFactory.getLogger(this::class.java)

    override val sourceKey: String = "incomplete-manga-guide"

    override fun searchManga(searchString: String): List<SearchResultDto> {
        val doc = Jsoup.parse(
            requestUtil.requestWebsiteWithRetry(
                url = "https://mangaguide.de/index.php?suche=$searchString&include=24",
            )
        )

        return doc.select("#inhalt").first()?.children()
            ?.let(this::getSearchEntryFromList) ?: listOf()
    }

    private fun getSearchEntryFromList(elements: Elements): List<SearchResultDto> {
        val indexStart = elements.indexOfFirst { it.toString().contains("<u>Mangas</u>") }
        var brCount = 0
        var indexEnd = 0

        for (element in elements) {
            if (element.toString().contains("<br>")) {
                brCount++
            } else {
                brCount = 0
            }
            if (brCount >= 3) {
                break
            }
            indexEnd++
        }

        val mangaElements = elements.take(indexEnd).drop(indexStart)

        if (indexStart == -1) {
            return listOf()
        }

        return runBlocking {
            coroutineScope {
                mangaElements.filter {
                    it.toString().contains("href")
                }.map {
                    async {
                        val id = "https://mangaguide.de/" + it.attr("href")
                        logger.info("started for id $id")
                        val coverImage = extractCoverImageFromSearch(id)
                        logger.info("finished for id $id")
                        SearchResultDto(
                            id,
                            it.text(),
                            listOf(),
                            coverImage
                        )
                    }
                }.awaitAll()
            }
        }


    }

    private suspend fun extractCoverImageFromSearch(mangaId: String): String {
        val doc = Jsoup.parse(
            requestUtil.requestWebsiteWithRetry(
                url = mangaId,
            )
        )
        delay(10000)

        return doc.getElementsByTag("img").first {
            it.attr("alt").contains("Frontcover")
        }.attr("src").let { url ->
            "https://mangaguide.de" + url.drop(1)
        }
    }

    override fun getMangaDetails(sourceId: String): NewMangaDto {
        val doc = Jsoup.parse(
            requestUtil.requestWebsiteWithRetry(
                url = sourceId,
            )
        )

        return extractDetailsFromWebsite(sourceId, doc)
    }

    private fun extractDetailsFromWebsite(mangaId: String, doc: Document): NewMangaDto {
        val title = doc.getElementsByClass("titel").first()?.text() ?: ""
        val imageUrl = doc.getElementsByTag("img").first {
            it.attr("alt").contains("Frontcover")
        }.attr("src").let { url ->
            "https://mangaguide.de" + url.drop(1)
        }
        val author = listOf<String>() //ToDo
        val publisher = "" //ToDo
        val releaseDate = extractDateFromBandText(
            doc.getElementsByClass("bandtext").first
        )
        val volumesInformation = extractVolumesInformation(mangaId, doc)

        return NewMangaDto(
            sourceKey,
            mangaId,
            title,
            imageUrl,
            author,
            publisher,
            releaseDate,
            volumesInformation.releasedVolumes,
            volumesInformation.japaneseVolumes,
            volumesInformation.finished,
            volumesInformation.finishedJapanese,
            volumesInformation.nextVolumeReleaseDate,
            volumesInformation.volumes
        )
    }

    private fun extractDateFromBandText(bandText: Element): Date? {
        val splitText = if (bandText.text().contains("Erschienen im ")) {
            "Erschienen im "
        } else if (bandText.text().contains("Angekündigt für ")) {
            "Angekündigt für "
        } else {
            return null
        }

        return dateUtil.convertGermanDateToObject(
            "1. " +
                bandText.text().split(splitText)[1]
                    .split(" (")[0]
        )
    }

    private fun extractVolumesInformation(mangaId: String, doc: Document): VolumeInformation {
        val pages = if (doc.getElementById("inhalt")?.text()?.contains("Seite: ") == true) {
            doc.getElementsByTag("a").last {
                it.attr("href").contains("seite")
            }.text().toInt()
        } else {
            1
        }

        val bandTextsAndCoverUrls = getBandTextsAndCoverUrls(mangaId, pages)
        val bandTexts = bandTextsAndCoverUrls.map { it.first }

        val releasedVolumes = bandTexts.count {
            it.text().contains("Der Band ist erschienen")
        }
        val japaneseVolumes = bandTexts.size
        val finished = bandTexts.any {
            it.text().contains("Letzter Band")
        }
        val finishedJapanese = finished

        val volumes = bandTextsAndCoverUrls.map {
            val sourceVolumeId = "" //ToDo is there one for this?
            val number = it.first.text().split("Band ")[1].split(" ")[0].toInt()
            val releaseDate = extractDateFromBandText(it.first)
            val released = releaseDate?.before(Date()) ?: false
            val imageUrl = it.second

            return@map NewVolumeDto(
                sourceVolumeId,
                number,
                releaseDate,
                released,
                imageUrl
            )
        }

        val nextVolumeReleaseDate = volumes.firstOrNull {
            it.releaseDate?.after(Date()) ?: false
        }?.releaseDate

        return VolumeInformation(
            releasedVolumes,
            japaneseVolumes,
            finished,
            finishedJapanese,
            nextVolumeReleaseDate,
            volumes
        )
    }

    private fun getBandTextsAndCoverUrls(mangaId: String, maxPage: Int): List<Pair<Element, String>> {
        val ret = mutableListOf<Pair<Element, String>>()

        for (page in 1..maxPage) {
            val doc = Jsoup.parse(
                requestUtil.requestWebsiteWithRetry(
                    url = "$mangaId&seite=$page",
                )
            )
            doc.getElementsByClass("mitte")[1].getElementsByTag("tr").filter { it ->
                it.getElementsByTag("hr").none()
            }.forEach {
                val bandText = it.getElementsByClass("bandtext").first
                val coverImage = it.getElementsByClass("cover").first
                    .getElementsByTag("img")
                    .firstOrNull()
                    ?.attr("src")?.let { url ->
                        "https://mangaguide.de" + url.drop(1)
                    }
                val japCoverImage = it.getElementsByClass("japcover").first
                    .getElementsByTag("img")
                    .first
                    .attr("src").let { url ->
                        "https://mangaguide.de" + url.drop(1)
                    }
                ret.add(Pair(bandText, coverImage ?: japCoverImage))
            }
        }

        return ret
    }
}