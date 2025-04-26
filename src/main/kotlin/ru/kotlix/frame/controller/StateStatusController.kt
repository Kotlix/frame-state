package ru.kotlix.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kotlix.frame.dto.ApiResponse
import ru.kotlix.frame.service.StateStatusService
import java.time.LocalDateTime

@RestController
@RequestMapping("api/status")
class StateStatusController(
    private val stateStatusService: StateStatusService
) {
    @GetMapping("/{userId}")
    fun getUserStatus(@PathVariable userId: Long): ResponseEntity<ApiResponse.Success<Boolean>> {
        return ResponseEntity.ok(
            ApiResponse.Success(
                data = stateStatusService.getUserStatus(userId)
            )
        )
    }

    @PutMapping("/{userId}")
    fun updateUserStatus(
        @PathVariable userId: Long,
        @RequestParam online: Boolean
    ): ResponseEntity<ApiResponse.Success<Nothing?>> {
        stateStatusService.updateUserStatus(userId, online)
        return ResponseEntity.ok(
            ApiResponse.Success(
                status = 204,
                data = null
            )
        )
    }

    @GetMapping("/{userId}/active")
    fun getUserLastActivity(@PathVariable userId: Long): ResponseEntity<ApiResponse.Success<LocalDateTime>> {
        return ResponseEntity.ok(
            ApiResponse.Success(
                data = stateStatusService.getUserLastActivity(userId)
            )
        )
    }

    @GetMapping("/online")
    fun getOnlineUsers(
        @RequestParam(required = false, defaultValue = "true") online: Boolean
    ): ResponseEntity<ApiResponse.Success<List<Long>>> {
        return ResponseEntity.ok(
            ApiResponse.Success(
                data = stateStatusService.getOnlineUsers(online)
            )
        )
    }
}