package ru.kotlix.frame.state.service.dto

import java.time.OffsetDateTime

data class UserState(
    val userId: Long,
    val online: Boolean,
    val lastActive: OffsetDateTime,
)
