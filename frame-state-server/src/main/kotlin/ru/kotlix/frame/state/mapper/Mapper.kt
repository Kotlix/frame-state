package ru.kotlix.frame.state.mapper

import ru.kotlix.frame.state.api.dto.UserState as ApiUserState
import ru.kotlix.frame.state.service.dto.UserState as ServiceUserState

fun ServiceUserState.toApi() =
    ApiUserState(
        userId = userId,
        online = online,
        lastActive = lastActive,
    )
