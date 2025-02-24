package com.github.zafkiel1312.mangaguidebackend.scraper.dto

import java.util.Date

data class DetailsDto(
    val title: String,
    val pictureUrl: String,
    val author: List<String>,
    val artist: List<String>,
    val publisher: List<String>,
    val releaseDate: Date,
    val releasedVolumes: Int,
    val japaneseVolumes: Int,
    val nextVolumeReleaseDate: Date?,
    val volumes: List<VolumeDetailsDto>
)
