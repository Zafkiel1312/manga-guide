package com.github.zafkiel1312.mangaguidebackend.publisher

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityIdIsNullException
import com.github.zafkiel1312.mangaguidebackend.manga.MangaEntity
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.PublisherDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.*

@Mapper(componentModel = "spring")
abstract class PublisherMapper {
    @Mapping(target = "mangaIds", source = "mangas")
    abstract fun convertToDto(entity: PublisherEntity): PublisherDto

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mangas", expression = "java(new java.util.ArrayList())")
    abstract fun convertToEntity(dto: CreatePublisherDto): PublisherEntity


    fun mangaToId(entity: MangaEntity): UUID =
        entity.id ?: throw EntityIdIsNullException("ID of Entity should not be null: $entity")
}