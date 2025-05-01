package ru.kotlix.frame.state.service

import ru.kotlix.frame.state.service.dto.UserState

interface UserStateService {
    fun getUserStatus(userId: Long): UserState

    fun updateUserStatus(
        userId: Long,
        online: Boolean,
    )
}
