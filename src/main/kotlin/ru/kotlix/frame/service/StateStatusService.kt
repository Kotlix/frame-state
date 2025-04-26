package ru.kotlix.frame.service

import org.springframework.stereotype.Service
import ru.kotlix.frame.exception.NotFoundException
import ru.kotlix.frame.repo.StateStatusRepository
import java.time.LocalDateTime

@Service
class StateStatusService(
    private val repo: StateStatusRepository
) {
    fun getUserStatus(userId: Long): Boolean {
        return repo.findStatusByUserId(userId)
            ?: throw NotFoundException("User status not found for user_id: $userId")
    }

    fun updateUserStatus(userId: Long, online: Boolean) {
        repo.updateStatusByUserId(userId, online)
    }

    fun getUserLastActivity(userId: Long): LocalDateTime {
        return repo.findLastActivityByUserId(userId)
            ?: throw NotFoundException("Last activity not found for user id: $userId")
    }

    fun getOnlineUsers(online: Boolean): List<Long> {
        return repo.findAllOnlineUserId(online)
            ?: emptyList()
    }
}