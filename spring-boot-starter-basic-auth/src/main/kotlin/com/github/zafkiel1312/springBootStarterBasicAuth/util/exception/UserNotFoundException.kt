package com.github.zafkiel1312.springBootStarterBasicAuth.util.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(
    message: String? = null,
    cause: Throwable? = null
): Exception(message, cause)