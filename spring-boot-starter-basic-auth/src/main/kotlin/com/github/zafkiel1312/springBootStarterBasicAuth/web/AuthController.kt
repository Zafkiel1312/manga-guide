package com.github.zafkiel1312.springBootStarterBasicAuth.web

import com.github.zafkiel1312.springBootStarterBasicAuth.auth.AppAuthManager
import com.github.zafkiel1312.springBootStarterBasicAuth.userDetails.AppUserDetailsService
import com.github.zafkiel1312.springBootStarterBasicAuth.web.dto.GetUserDto
import com.github.zafkiel1312.springBootStarterBasicAuth.web.dto.AddUserDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class AuthController(
    val appUserDetailsService: AppUserDetailsService,
    val appAuthManager: AppAuthManager
) {

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun register(@RequestBody addUserDto: AddUserDto): UUID =
        appUserDetailsService.addNewUser(addUserDto)

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun addNewUser(@PathVariable id: UUID) =
        appUserDetailsService.deleteUser(id)

    @GetMapping("/currentUser")
    fun getCurrentUser(): String =
        appAuthManager.getCurrentUser().username

    @GetMapping("/users")
    fun getAllUsers(): List<GetUserDto> =
        appUserDetailsService.getAllUsers()

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: UUID): GetUserDto =
        appUserDetailsService.findUserById(id)
}