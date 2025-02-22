package com.github.zafkiel1312.mangaguidebackend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class EntityIdIsNullException(
    message: String? = null,
    cause: Throwable? = null
): RuntimeException(message, cause)