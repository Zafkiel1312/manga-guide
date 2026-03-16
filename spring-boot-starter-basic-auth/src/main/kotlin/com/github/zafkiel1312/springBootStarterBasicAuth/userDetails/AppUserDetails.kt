package com.github.zafkiel1312.springBootStarterBasicAuth.userDetails

import com.github.zafkiel1312.springBootStarterBasicAuth.util.StringListConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "starter_basic_auth_users")
data class AppUserDetails(
    @Id
    @GeneratedValue
    val id: UUID,
    @Column(
        unique = true
    )
    val username: String,
    val password: String,
    val forename: String,
    val surname: String,
    @Convert(converter = StringListConverter::class)
    val groups: List<String>,
    @Convert(converter = StringListConverter::class)
    val roles: List<String> // can be TUTOR, TRAINEE, MANAGER, ADMIN
)
