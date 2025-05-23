package ru.kotlix.frame.state.service

import feign.FeignException
import org.springframework.stereotype.Service
import ru.kotlix.frame.state.exception.NotFoundException
import ru.kotlix.frame.state.repository.UserStateRepository
import ru.kotlix.frame.state.service.dto.UserState
import ru.kotlix.frame.voice.api.dto.LeaveRequest
import ru.kotlix.frame.voice.client.VoiceClient

@Service
class UserStateServiceImpl(
    private val repo: UserStateRepository,
    private val voiceClient: VoiceClient,
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

        if (online) {
            return
        }
        try {
            voiceClient.leaveChannel(
                LeaveRequest(
                    userId = userId,
                ),
            )
        } catch (ignored: FeignException.NotFound) {
        }
    }
}
