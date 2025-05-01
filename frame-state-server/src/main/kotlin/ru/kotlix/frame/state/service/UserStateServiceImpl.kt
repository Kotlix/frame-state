package ru.kotlix.frame.state.service

import org.springframework.stereotype.Service
import ru.kotlix.frame.state.exception.NotFoundException
import ru.kotlix.frame.state.repository.UserStateRepository
import ru.kotlix.frame.state.service.dto.UserState

@Service
class UserStateServiceImpl(
    private val repo: UserStateRepository,
) : UserStateService {
    override fun getUserStatus(userId: Long): UserState {
        return repo.findStatusByUserId(userId)
            ?: throw NotFoundException("User status not found for user_id: $userId")
    }

    override fun updateUserStatus(
        userId: Long,
        online: Boolean,
    ) {
        repo.updateStatusByUserId(userId, online)
    }
}
