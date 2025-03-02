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
    val sourceKey: String,
    val sourceMangaId: String,
    val title: String,
    val imageUrl: String,
    @ElementCollection
    val author: List<String>,
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    var publisher: PublisherEntity,
    val releaseDate: Date?,
    var releasedVolumes: Int,
    var boughtVolumes: Int,
    var japaneseVolumes: Int,
    var finished: Boolean,
    var finishedJapanese: Boolean,
    var nextVolumeReleaseDate: Date?,
    @OneToMany(mappedBy = "manga")
    val volumes: MutableList<VolumeEntity>
)