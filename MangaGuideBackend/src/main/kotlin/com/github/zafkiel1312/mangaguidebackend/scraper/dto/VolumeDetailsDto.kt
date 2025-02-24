package com.github.zafkiel1312.mangaguidebackend.scraper.dto

import java.util.*

data class VolumeDetailsDto(
    val name: String,
    val number: Int,
    val releaseDate: Date?,
    val released: Boolean,
    val imageUrl: String
)
