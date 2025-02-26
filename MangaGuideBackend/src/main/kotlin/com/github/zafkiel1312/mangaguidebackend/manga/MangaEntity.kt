package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherEntity
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "manga")
class MangaEntity(
    @Id @GeneratedValue
    val id: UUID? = null,
    val mangaPassionId: Long,
    val title: String,
    val imageUrl: String,
    @ElementCollection
    val author: List<String>,
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    var publisher: PublisherEntity,
    val releaseDate: Date?,
    val releasedVolumes: Int,
    val boughtVolumes: Int,
    val japaneseVolumes: Int,
    val finished: Boolean,
    val finishedJapanese: Boolean,
    val nextVolumeReleaseDate: Date?,
    @OneToMany(mappedBy = "manga")
    val volumes: MutableList<VolumeEntity>
)