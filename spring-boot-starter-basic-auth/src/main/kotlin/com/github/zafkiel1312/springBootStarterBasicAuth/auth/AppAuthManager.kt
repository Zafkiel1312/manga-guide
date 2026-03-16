package com.github.zafkiel1312.springBootStarterBasicAuth.auth

import com.github.zafkiel1312.springBootStarterBasicAuth.userDetails.AppUserPrinciple
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AppAuthManager {
    fun getCurrentUser(): AppUserPrinciple =
        SecurityContextHolder.getContext().authentication.principal as AppUserPrinciple
}