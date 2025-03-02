package com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper

import com.github.zafkiel1312.mangaguidebackend.sources.dto.SearchResultDto
import com.github.zafkiel1312.mangaguidebackend.sources.util.RequestUtil
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class ImgScraperService {

    private val requestUtil = RequestUtil()

    fun searchByString(searchString: String): List<SearchResultDto> {
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

        return mangaElements.filter {
            it.toString().contains("href")
        }.map {
            SearchResultDto(
                it.attr("href"),
                it.text(),
                listOf(),
                "https://mangaguide.de/bilder/frontcover_gross/9093.jpg"
            )
        }
    }
}