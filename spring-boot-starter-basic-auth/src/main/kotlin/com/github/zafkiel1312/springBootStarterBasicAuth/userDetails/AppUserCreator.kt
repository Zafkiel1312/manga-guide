package com.github.zafkiel1312.springBootStarterBasicAuth.userDetails

import com.github.zafkiel1312.springBootStarterBasicAuth.config.BasicAuthProperties
import com.github.zafkiel1312.springBootStarterBasicAuth.web.dto.AddUserDto
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserCreator(
    val basicAuthProperties: BasicAuthProperties,
    val appUserDetailsService: AppUserDetailsService
) {

    @PostConstruct
    fun createAdminUser() {
        val newUsers = mutableListOf<AddUserDto>()

        if (basicAuthProperties.createDefaultAdmin) {
            newUsers.add(
                generateAddUserDto("admin")
            )
        }

        if (basicAuthProperties.createDefaultManager) {
            newUsers.add(
                generateAddUserDto("manager")
            )
        }

        if (basicAuthProperties.createDefaultTutor) {
            newUsers.add(
                generateAddUserDto("tutor")
            )
        }

        if (basicAuthProperties.createDefaultTrainee) {
            newUsers.add(
                generateAddUserDto("trainee")
            )
        }

        newUsers.forEach {
            appUserDetailsService.addNewUser(it)
        }

    }

    private fun generateAddUserDto(role: String) =
        AddUserDto(
            role,
            role,
            role,
            role,
            listOf(),
            listOf(role.uppercase(Locale.getDefault()))
        )
}