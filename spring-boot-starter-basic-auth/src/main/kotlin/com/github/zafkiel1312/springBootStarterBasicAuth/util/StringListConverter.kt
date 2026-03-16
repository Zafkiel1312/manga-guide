package com.github.zafkiel1312.springBootStarterBasicAuth.util

import jakarta.persistence.AttributeConverter

class StringListConverter: AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(list: List<String>): String =
        list.joinToString(",")

    override fun convertToEntityAttribute(string: String): List<String> =
        string.split(",")
}