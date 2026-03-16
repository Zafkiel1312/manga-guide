package com.github.zafkiel1312.springBootStarterBasicAuth.web.dto

data class AddUserDto(
    val username: String,
    val password: String,
    val forename: String,
    val surname: String,
    val groups: List<String>,
    val roles: List<String>
)
