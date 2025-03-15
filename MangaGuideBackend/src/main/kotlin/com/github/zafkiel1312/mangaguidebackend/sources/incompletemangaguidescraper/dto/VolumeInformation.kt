package com.github.zafkiel1312.mangaguidebackend.sources.incompletemangaguidescraper.dto

import com.github.zafkiel1312.mangaguidebackend.sources.dto.NewVolumeDto
import java.util.*

data class VolumeInformation(
    val releasedVolumes: Int,
    val japaneseVolumes: Int,
    val finished: Boolean,
    val finishedJapanese: Boolean,
    val nextVolumeReleaseDate: Date?,
    val volumes: List<NewVolumeDto>
)
