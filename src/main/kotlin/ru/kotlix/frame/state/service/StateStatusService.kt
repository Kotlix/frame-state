package ru.kotlix.frame.state.service

import org.springframework.stereotype.Service
import ru.kotlix.frame.state.dto.UserState
import ru.kotlix.frame.state.exception.NotFoundException
import ru.kotlix.frame.state.repo.StateStatusRepository

@Service
class StateStatusService(
    private val repo: StateStatusRepository
) {
    fun getUserStatus(userId: Long): UserState {
        return repo.findStatusByUserId(userId)
            ?: throw NotFoundException("User status not found for user_id: $userId")
    }

    fun updateUserStatus(userId: Long, online: Boolean) {
        repo.updateStatusByUserId(userId, online)
    }
}