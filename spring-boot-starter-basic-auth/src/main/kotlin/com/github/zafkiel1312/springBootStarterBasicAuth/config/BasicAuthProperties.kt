package com.github.zafkiel1312.springBootStarterBasicAuth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "com.github.zafkiel1312.basic-auth")
class BasicAuthProperties (
    var createDefaultAdmin: Boolean = false,
    var createDefaultTutor: Boolean = false,
    var createDefaultManager: Boolean = false,
    var createDefaultTrainee: Boolean = false,
)