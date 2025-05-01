package ru.kotlix.frame.state.api.dto

import java.time.OffsetDateTime

data class UserState(
    val userId: Long,
    val online: Boolean,
    val lastActive: OffsetDateTime,
)
