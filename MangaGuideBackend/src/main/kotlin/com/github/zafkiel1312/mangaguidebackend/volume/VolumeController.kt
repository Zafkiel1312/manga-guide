package com.github.zafkiel1312.mangaguidebackend.volume

import com.github.zafkiel1312.mangaguidebackend.volume.dto.VolumeDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/volumes")
class VolumeController(
    private val volumeService: VolumeService
) {
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @Transactional
//    fun createVolume(@RequestBody createVolumeDto: CreateVolumeDto): UUID =
//        volumeService.createVolume(createVolumeDto)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllVolumes(): List<VolumeDto> =
        volumeService.getAllVolumes()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getVolumeById(@PathVariable id: UUID): VolumeDto =
        volumeService.getVolumeDtoById(id)
}