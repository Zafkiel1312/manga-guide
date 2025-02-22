package com.github.zafkiel1312.mangaguidebackend.manga

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityIdIsNullException
import com.github.zafkiel1312.mangaguidebackend.manga.dto.CreateMangaDto
import com.github.zafkiel1312.mangaguidebackend.manga.dto.MangaDto
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherEntity
import com.github.zafkiel1312.mangaguidebackend.publisher.PublisherService
import com.github.zafkiel1312.mangaguidebackend.volume.VolumeEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.*

@Mapper(componentModel = "spring", uses = [PublisherService::class])
abstract class MangaMapper {
    @Mapping(target = "publisherId", source = "publisher")
    @Mapping(target = "volumeIds", source = "volumes")
    abstract fun convertToDto(entity: MangaEntity): MangaDto

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publisher", source = "publisherId")
    @Mapping(target = "volumes", expression = "java(new java.util.ArrayList())")
    abstract fun convertToEntity(dto: CreateMangaDto): MangaEntity


    fun publisherToId(entity: PublisherEntity): UUID =
        entity.id ?: throw EntityIdIsNullException("ID of Entity should not be null: $entity")

    fun volumeToId(entity: VolumeEntity): UUID =
        entity.id ?: throw EntityIdIsNullException("ID of Entity should not be null: $entity")
}