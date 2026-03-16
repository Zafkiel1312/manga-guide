package com.github.zafkiel1312.springBootStarterBasicAuth.userDetails

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AppUserPrinciple(
    private val user: AppUserDetails
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        user.roles.map {
            SimpleGrantedAuthority(it)
        }.toMutableList()


    override fun getPassword(): String =
        user.password


    override fun getUsername(): String =
        user.username
}