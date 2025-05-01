package ru.kotlix.frame.state.api

import ru.kotlix.frame.state.api.dto.UpdateUserStatusRequest
import ru.kotlix.frame.state.api.dto.UserState

interface UserStateApi {
    fun getUserStatus(userId: Long): UserState

    fun updateUserStatus(
        userId: Long,
        request: UpdateUserStatusRequest,
    )
}
