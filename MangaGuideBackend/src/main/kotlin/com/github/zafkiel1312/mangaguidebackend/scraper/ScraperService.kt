package com.github.zafkiel1312.mangaguidebackend.scraper

import com.github.zafkiel1312.mangaguidebackend.scraper.dto.DetailsDto
import com.github.zafkiel1312.mangaguidebackend.scraper.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.scraper.dto.VolumeDetailsDto
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Date

@Service
class ScraperService(
    private val requestUtil: RequestUtil
) {
    private val dateUtil = DateUtil()

    fun searchByString(searchString: String): List<SearchResultDto> {
        val doc = Jsoup.parse(
            requestUtil.requestWebsiteWithRetry(
                url = "https://www.manga-passion.de/search?q=$searchString",
                shouldNotContain = listOf(
                    "https://media.manga-passion.de/hosting/img/common/none.png"
                )
            )
        )

        return doc.getElementsByClass("manga-list_listItem__4CHgZ")
            .map(this::extractDataFromListItem)
    }

    private fun extractDataFromListItem(listItem: Element): SearchResultDto {
        val detailsUrl = "https://www.manga-passion.de" +
            listItem.attr("href")
        val imageUrl = "https://www.manga-passion.de" +
            listItem.getElementsByClass("img_img__jkdIh").first.attr("src")
        val name = listItem.getElementsByClass("manga-list_title__GKlEd").first.text()

        val author = listItem.getElementsByClass("manga-list_field__7ZeLA").first {
            it.toString().contains("Autor/Zeichner")
        }.getElementsByClass("manga-list_fieldContentRow__FUjXT").map {
            it.text()
        }

        val publisher = listItem.getElementsByClass("manga-list_field__7ZeLA").first {
            it.toString().contains("Verlag")
        }.getElementsByClass("manga-list_fieldContentRow__FUjXT").map {
            it.text()
        }

        return SearchResultDto(
            name,
            author,
            publisher,
            imageUrl,
            detailsUrl
        )
    }

    fun getDetails(detailsUrl: String): DetailsDto {
        val doc = Jsoup.parse(
            requestUtil.requestWebsiteWithRetry(
                url = detailsUrl,
                shouldContain = listOf(
                    "manga-list_tileItemWrapper__qR2Dl"
                ),
                scrollDown = true
            )
        )

        return extractDetails(doc)
    }

    private fun extractDetails(detailsPage: Element): DetailsDto {
        val title = detailsPage.getElementsByClass("manga_mainHeadingWrapper__sGPUj").first
            .getElementsByTag("h1").first.text()
        val pictureUrl = "https://www.manga-passion.de" +
            detailsPage.getElementsByClass("img_img__jkdIh").first.attr("src")
        val releaseDate = dateUtil.convertGermanDateToObject(
            detailsPage.getElementsByClass("manga-list_tileItemBorderTextFlex__oykGD")[1].text()
        )

        val mangaDetails = detailsPage.getElementsByClass("manga_details__UYMcm")

        val author = mangaDetails.first {
            it.toString().contains("Autor")
        }.getElementsByTag("a").map {
            it.text()
        }

        val artist = mangaDetails.first {
            it.toString().contains("Zeichner")
        }.getElementsByTag("a").map {
            it.text()
        }

        val publisher = detailsPage.getElementsByClass("manga_mangaInfoItem___pa9z").first {
            it.toString().contains("Verlag")
        }.getElementsByTag("a").map {
            it.text()
        }

        val volumeElements = detailsPage.getElementsByClass("manga-list_tileItem__nPV2X")

        val japaneseVolumes = volumeElements.size
        val releasedVolumes = volumeElements.filter { it ->
            !it.toString().contains("TBA")
        }.filter {
            dateUtil.convertGermanDateToObject(
                it.getElementsByClass("manga-list_tileItemBorderTextFlex__oykGD")[1].text()
            ).before(Date())
        }.size

        val nextVolumeReleaseDate = volumeElements.mapNotNull {
            val dateElements = it.getElementsByClass("manga-list_tileItemBorderTextFlex__oykGD")
            if (dateElements.size < 2) {
                null
            } else {
                val germanDate = dateElements[1].text()
                dateUtil.convertGermanDateToObject(germanDate)
            }
        }.firstOrNull {
            it.after(Date())
        }

        val volumeDetails = volumeElements.map(this::extractVolumeDetails)

        return DetailsDto(
            title,
            pictureUrl,
            author,
            artist,
            publisher,
            releaseDate,
            releasedVolumes,
            japaneseVolumes,
            nextVolumeReleaseDate,
            volumeDetails
        )
    }

    private fun extractVolumeDetails(detailsVolume: Element): VolumeDetailsDto {
        val textFields = detailsVolume.getElementsByClass("manga-list_tileItemBorderTextFlex__oykGD")
        val name = textFields.first.text()
        val number = name.split(" ").last().toInt()
        val releaseDate = if (textFields.size > 1) {
            dateUtil.convertGermanDateToObject(textFields[1].text())
        } else null
        val released = releaseDate?.before(Date()) ?: false
        val imageUrl = "https://www.manga-passion.de" +
            detailsVolume.getElementsByClass("img_img__jkdIh").first.attr("src")

        return VolumeDetailsDto(
            name,
            number,
            releaseDate,
            released,
            imageUrl
        )
    }


}