package com.github.zafkiel1312.mangaguidebackend.publisher

import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.PublisherDto
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/publisher")
class PublisherController(
    private val publisherService: PublisherService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createPublisher(@RequestBody createPublisherDto: CreatePublisherDto): UUID =
        publisherService.createPublisher(createPublisherDto)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPublishers(): List<PublisherDto> =
        publisherService.getAllPublishers()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPublisherDtoById(@PathVariable id: UUID): PublisherDto =
        publisherService.getPublisherDtoById(id)
}