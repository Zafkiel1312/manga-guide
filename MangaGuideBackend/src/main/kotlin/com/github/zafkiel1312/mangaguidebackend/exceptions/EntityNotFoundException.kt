package com.github.zafkiel1312.mangaguidebackend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityNotFoundException(
    message: String? = null,
    cause: Throwable? = null
): RuntimeException(message, cause)