package com.github.zafkiel1312.springBootStarterBasicAuth.web.dto

import java.util.UUID

data class GetUserDto(
    val id: UUID,
    val username: String,
    val forename: String,
    val surname: String,
    val groups: List<String>,
    val roles: List<String>
)