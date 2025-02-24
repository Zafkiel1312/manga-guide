package com.github.zafkiel1312.mangaguidebackend.mangapassion

import com.github.zafkiel1312.mangaguidebackend.mangapassion.dto.edition.EditionResponseDto
import com.github.zafkiel1312.mangaguidebackend.mangapassion.dto.volume.VolumeResponseDto
import com.github.zafkiel1312.mangaguidebackend.mangapassion.params.EditionParams
import com.github.zafkiel1312.mangaguidebackend.mangapassion.params.VolumeParams
import feign.Param
import feign.QueryMap
import feign.RequestLine

interface MangaPassionClient {
    @RequestLine("GET /editions")
    fun getEditions(@QueryMap params: EditionParams): List<EditionResponseDto>

    @RequestLine("GET /editions/{id}")
    fun getEditionById(@Param("id") id: Long): EditionResponseDto?

    @RequestLine("GET /editions/{id}/volumes")
    fun getVolumesOfEdition(@Param id: Long, @QueryMap params: VolumeParams): List<VolumeResponseDto>
}