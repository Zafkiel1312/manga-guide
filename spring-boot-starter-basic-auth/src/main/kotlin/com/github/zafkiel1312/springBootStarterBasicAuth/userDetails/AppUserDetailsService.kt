package com.github.zafkiel1312.springBootStarterBasicAuth.userDetails

import com.github.zafkiel1312.springBootStarterBasicAuth.util.exception.UserNotFoundException
import com.github.zafkiel1312.springBootStarterBasicAuth.web.dto.GetUserDto
import com.github.zafkiel1312.springBootStarterBasicAuth.web.dto.AddUserDto
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserDetailsService(
    val appUserDetailsRepository: AppUserDetailsRepository
): UserDetailsService {

    private val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("Username cannot be null")
        return appUserDetailsRepository.findByUsername(username)?.let {
            AppUserPrinciple(it)
        } ?: throw UsernameNotFoundException("Could not find user with username $username")
    }

    fun addNewUser(addUserDto: AddUserDto): UUID =
        appUserDetailsRepository.save(
            AppUserDetails(
                UUID.randomUUID(),
                addUserDto.username,
                passwordEncoder.encode(addUserDto.password),
                addUserDto.forename,
                addUserDto.surname,
                addUserDto.groups,
                addUserDto.roles
            )
        ).id

    fun deleteUser(id: UUID) {
        appUserDetailsRepository.deleteById(id)
    }

    fun findUserById(id: UUID): GetUserDto =
        appUserDetailsRepository.findById(id).orElseThrow {
            UserNotFoundException("No user with the id $id was found")
        }.let {
            GetUserDto(
                it.id,
                it.username,
                it.forename,
                it.surname,
                it.groups,
                it.roles
            )
        }

    fun getAllUsers() =
        appUserDetailsRepository.findAll().map {
            GetUserDto(
                it.id,
                it.username,
                it.forename,
                it.surname,
                it.groups,
                it.roles
            )
        }
}