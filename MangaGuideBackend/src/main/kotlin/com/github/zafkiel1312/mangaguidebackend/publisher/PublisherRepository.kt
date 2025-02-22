package com.github.zafkiel1312.mangaguidebackend.publisher

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PublisherRepository: JpaRepository<PublisherEntity, UUID> {
}