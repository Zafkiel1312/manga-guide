package com.github.zafkiel1312.mangaguidebackend.sources.dto

import java.util.*

data class NewVolumeDto(
    val sourceVolumeId: String,
    val number: Int,
    val releaseDate: Date?,
    val released: Boolean,
    val imageUrl: String
)