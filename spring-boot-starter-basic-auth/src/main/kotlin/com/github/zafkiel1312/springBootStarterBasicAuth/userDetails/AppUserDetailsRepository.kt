package com.github.zafkiel1312.springBootStarterBasicAuth.userDetails

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface AppUserDetailsRepository: JpaRepository<AppUserDetails, UUID> {
    fun findByUsername(username: String): AppUserDetails?
}