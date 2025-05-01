package ru.kotlix.frame.state.repository

import ru.kotlix.frame.state.service.dto.UserState

interface UserStateRepository {
    fun findStatusByUserId(userId: Long): UserState?

    fun updateStatusByUserId(
        userId: Long,
        online: Boolean,
    )
}
