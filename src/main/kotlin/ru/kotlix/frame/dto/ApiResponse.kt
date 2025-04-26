package ru.kotlix.frame.dto

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

sealed class ApiResponse<T>(
    open val status: Int,
    open val data: T? = null,
    open val timestamp: LocalDateTime = LocalDateTime.now()
) {
    data class Success<T>(
        override val data: T,
        override val status: Int = HttpStatus.OK.ordinal,
        override val timestamp: LocalDateTime = LocalDateTime.now()
    ) : ApiResponse<T>(status, data, timestamp)

    data class Error(
        override val status: Int,
        val error: String,
        val message: String,
        override val timestamp: LocalDateTime = LocalDateTime.now()
    ) : ApiResponse<Nothing>(status)
}