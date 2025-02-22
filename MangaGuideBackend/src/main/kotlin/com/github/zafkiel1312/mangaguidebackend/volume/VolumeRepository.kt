package com.github.zafkiel1312.mangaguidebackend.volume

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VolumeRepository: JpaRepository<VolumeEntity, UUID> {
}