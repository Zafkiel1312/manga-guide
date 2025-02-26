package com.github.zafkiel1312.mangaguidebackend.manga.dto

import java.util.*

data class CreateMangaDto(
    val mangaPassionId: Long,
    val title: String,
    val imageUrl: String,
    val author: List<String>,
    val publisherId: UUID,
    val releaseDate: Date?,
    val releasedVolumes: Int,
    val boughtVolumes: Int,
    val japaneseVolumes: Int,
    val finished: Boolean,
    val finishedJapanese: Boolean,
    val nextVolumeReleaseDate: Date?
)
