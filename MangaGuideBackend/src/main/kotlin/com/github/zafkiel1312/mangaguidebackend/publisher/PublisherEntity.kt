package com.github.zafkiel1312.mangaguidebackend.publisher

import com.github.zafkiel1312.mangaguidebackend.manga.MangaEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "publisher")
class PublisherEntity(
    @Id @GeneratedValue
    val id: UUID? = null,
    val name: String,
    val pictureUrl: String,
    @OneToMany(mappedBy = "publisher")
    val mangas: List<MangaEntity>
)