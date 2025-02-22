package com.github.zafkiel1312.mangaguidebackend.volume.dto

import java.util.*

data class CreateVolumeDto(
    var mangaId: UUID,
    val number: Int,
    val releaseDate: Date,
    val released: Boolean
)
