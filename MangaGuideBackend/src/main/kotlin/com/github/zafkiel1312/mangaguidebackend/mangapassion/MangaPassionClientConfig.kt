package com.github.zafkiel1312.mangaguidebackend.mangapassion

import feign.Feign
import feign.jackson.JacksonDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MangaPassionClientConfig {
    @Bean
    fun mangaPassionClient(): MangaPassionClient =
        Feign.builder()
            .decoder(JacksonDecoder())
            .target(MangaPassionClient::class.java, "https://api.manga-passion.de")
}