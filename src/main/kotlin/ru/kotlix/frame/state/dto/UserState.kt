package ru.kotlix.frame.state.dto

import java.time.LocalDateTime

data class UserState(
    val userId: Long,
    val online: Boolean,
    val lastActive: LocalDateTime
)

