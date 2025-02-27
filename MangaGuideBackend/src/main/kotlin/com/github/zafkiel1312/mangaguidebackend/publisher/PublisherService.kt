package com.github.zafkiel1312.mangaguidebackend.publisher

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.CreatePublisherDto
import com.github.zafkiel1312.mangaguidebackend.publisher.dto.PublisherDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class PublisherService(
    private val publisherRepository: PublisherRepository,
    private val publisherMapper: PublisherMapper
) {
    @Transactional
    fun createPublisher(createPublisherDto: CreatePublisherDto): UUID =
        publisherMapper.convertToEntity(createPublisherDto).let {
            publisherRepository.save(it)
        }.id!!

    fun getAllPublishers(): List<PublisherDto> =
        publisherRepository.findAll()
            .map(publisherMapper::convertToDto)


    fun getPublisherDtoById(id: UUID): PublisherDto =
        getPublisherById(id).let(publisherMapper::convertToDto)

    fun getPublisherById(id: UUID): PublisherEntity =
        publisherRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Publisher with id $id could not be found")
        }

    fun getPlaceholderPublisher(placeholder: String): PublisherEntity =
        publisherRepository.findAll().first()
}