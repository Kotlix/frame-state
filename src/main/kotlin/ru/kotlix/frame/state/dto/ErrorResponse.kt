package ru.kotlix.frame.state.dto

import java.time.LocalDateTime

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)