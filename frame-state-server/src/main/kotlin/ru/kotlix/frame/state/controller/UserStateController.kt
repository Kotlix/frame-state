package ru.kotlix.frame.state.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlix.frame.state.api.UserStateApi
import ru.kotlix.frame.state.api.dto.UpdateUserStatusRequest
import ru.kotlix.frame.state.api.dto.UserState
import ru.kotlix.frame.state.mapper.toApi
import ru.kotlix.frame.state.service.UserStateService

@RestController
@RequestMapping("/api/v1")
class UserStateController(
    private val stateStatusService: UserStateService,
) : UserStateApi {
    @GetMapping("/status/{userId}")
    override fun getUserStatus(
        @PathVariable
        userId: Long,
    ): UserState {
        return stateStatusService.getUserStatus(userId).toApi()
    }

    @PutMapping("/status/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateUserStatus(
        @PathVariable
        userId: Long,
        @RequestBody
        request: UpdateUserStatusRequest,
    ) {
        stateStatusService.updateUserStatus(userId, request.online)
    }
}
