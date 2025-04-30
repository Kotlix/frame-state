package ru.kotlix.frame.state.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kotlix.frame.state.dto.ApiResponse
import ru.kotlix.frame.state.dto.UserState
import ru.kotlix.frame.state.service.StateStatusService

@RestController
@RequestMapping("api/status")
class StateStatusController(
    private val stateStatusService: StateStatusService
) {
    @GetMapping("/{userId}")
    fun getUserStatus(@PathVariable userId: Long): ResponseEntity<ApiResponse.Success<UserState>> {
        return ResponseEntity.ok(
            ApiResponse.Success(
                data = stateStatusService.getUserStatus(userId)
            )
        )
    }

    @PutMapping("/update/{userId}")
    fun updateUserStatus(
        @PathVariable userId: Long,
        @RequestBody request: UpdateUserStatusRequest
    ): ResponseEntity<ApiResponse.Success<Nothing?>> {
        stateStatusService.updateUserStatus(userId, request.online)
        return ResponseEntity.ok(
            ApiResponse.Success(
                status = 204,
                data = null
            )
        )
    }
}

data class UpdateUserStatusRequest(
    val online: Boolean
)
