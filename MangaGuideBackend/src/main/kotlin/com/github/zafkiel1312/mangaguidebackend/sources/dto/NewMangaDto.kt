package com.github.zafkiel1312.mangaguidebackend.sources.dto

import java.util.*

data class NewMangaDto(
    val sourceKey: String,
    val sourceMangaId: String,
    val title: String,
    val imageUrl: String,
    val author: List<String>,
    val publisher: String,
    val releaseDate: Date?,
    val releasedVolumes: Int,
    val japaneseVolumes: Int,
    val finished: Boolean,
    val finishedJapanese: Boolean,
    val nextVolumeReleaseDate: Date?,
    val volumes: List<NewVolumeDto>
)
