package com.github.zafkiel1312.mangaguidebackend.sources.scraper

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class DateUtil {
    private val monthMap = mapOf(
        "Januar" to 1,
        "Februar" to 2,
        "März" to 3,
        "April" to 4,
        "Mai" to 5,
        "Juni" to 6,
        "Juli" to 7,
        "August" to 8,
        "September" to 9,
        "Oktober" to 10,
        "November" to 11,
        "Dezember" to 12,
    )
    fun convertGermanDateToObject(germanDate: String): Date {
        val dayExists = germanDate.contains(".")
        val day = if (dayExists) germanDate.split(".").first().toInt() else 1
        val germanMonth = germanDate.split(" ")[if (dayExists) 1 else 0]
        val month = monthMap[germanMonth] ?: 1
        val year = germanDate.split(" ").last().toInt()
        val localDate = LocalDate.of(year, month, day)

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

    }
}