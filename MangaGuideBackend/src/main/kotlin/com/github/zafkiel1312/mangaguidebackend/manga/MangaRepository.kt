package com.github.zafkiel1312.mangaguidebackend.manga

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MangaRepository: JpaRepository<MangaEntity, UUID> {
}