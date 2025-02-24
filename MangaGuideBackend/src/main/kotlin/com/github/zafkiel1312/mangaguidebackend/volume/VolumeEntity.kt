package com.github.zafkiel1312.mangaguidebackend.volume

import com.github.zafkiel1312.mangaguidebackend.manga.MangaEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "volume")
class VolumeEntity(
    @Id @GeneratedValue
    val id: UUID? = null,
    @ManyToOne
    @JoinColumn(name = "manga_id", nullable = false)
    var manga: MangaEntity,
    val number: Int,
    val releaseDate: Date?,
    val released: Boolean,
    val imageUrl: String,
)