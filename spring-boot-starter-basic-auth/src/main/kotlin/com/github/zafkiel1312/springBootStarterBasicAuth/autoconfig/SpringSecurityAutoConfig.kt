package com.github.zafkiel1312.springBootStarterBasicAuth.autoconfig

import com.github.zafkiel1312.springBootStarterBasicAuth.config.BasicAuthProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource

@EnableConfigurationProperties(BasicAuthProperties::class)
@ComponentScan(basePackages = ["com.github.zafkiel1312.springBootStarterBasicAuth"])
@PropertySource(value = ["classpath:basic-auth-application.yml"])
class SpringSecurityAutoConfig {
}