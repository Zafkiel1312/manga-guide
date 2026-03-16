package com.github.zafkiel1312.springBootStarterBasicAuth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class BasicAuthConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf(AbstractHttpConfigurer<CsrfConfigurer<HttpSecurity>,HttpSecurity>::disable)
            .authorizeHttpRequests { authorize ->
                authorize.requestMatchers("/public/**").permitAll()
                authorize.anyRequest().authenticated()
            }.httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())

        return http.build()
    }

}
