package com.github.zafkiel1312.mangaguidebackend.volume

import com.github.zafkiel1312.mangaguidebackend.exceptions.EntityNotFoundException
import com.github.zafkiel1312.mangaguidebackend.volume.dto.CreateVolumeDto
import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class VolumeService(
    private val volumeRepository: VolumeRepository,
    private val volumeMapper: VolumeMapper
) {
    @Transactional
    fun createVolume(createVolumeDto: CreateVolumeDto): UUID =
        volumeMapper.convertToEntity(createVolumeDto).let {
            volumeRepository.save(it)
        }.id!!

    fun getAllVolumes(): List<VolumeDto> =
        volumeRepository.findAll()
            .map(volumeMapper::convertToDto)

    fun getVolumeDtoById(id: UUID): VolumeDto =
        getVolumeById(id).let(volumeMapper::convertToDto)

    fun getVolumeById(id: UUID): VolumeEntity =
        volumeRepository.findById(id).orElseThrow{
            throw EntityNotFoundException("Volume with id $id could not be found")
        }
}