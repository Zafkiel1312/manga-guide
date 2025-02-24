package com.github.zafkiel1312.mangaguidebackend.volume

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityIdIsNullException
import com.github.zafkiel1312.mangaguidebackend.manga.MangaEntity
import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.*

@Mapper(componentModel = "spring")
abstract class VolumeMapper {
    @Mapping(target = "mangaId", source = "manga")
    abstract fun convertToDto(entity: VolumeEntity): VolumeDto

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "manga", source = "mangaId")
//    abstract fun convertToEntity(dto: CreateVolumeDto): VolumeEntity


    fun mangaToId(entity: MangaEntity): UUID =
        entity.id ?: throw EntityIdIsNullException("ID of Entity should not be null: $entity")
}