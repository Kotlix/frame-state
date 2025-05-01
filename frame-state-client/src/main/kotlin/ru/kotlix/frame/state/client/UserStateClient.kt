package ru.kotlix.frame.state.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import ru.kotlix.frame.state.api.UserStateApi
import ru.kotlix.frame.state.api.dto.UpdateUserStatusRequest
import ru.kotlix.frame.state.api.dto.UserState

@FeignClient(name = "frame-state-client", path = "/api/v1")
interface UserStateClient : UserStateApi {
    @GetMapping("/status/{userId}")
    override fun getUserStatus(
        @PathVariable
        userId: Long,
    ): UserState

    @PutMapping("/status/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun updateUserStatus(
        @PathVariable
        userId: Long,
        @RequestBody
        request: UpdateUserStatusRequest,
    )
}
