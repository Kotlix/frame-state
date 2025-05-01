package ru.kotlix.frame.state.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.kotlix.frame.state.service.dto.UserState
import java.time.OffsetDateTime

@Repository
class UserStateRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
) : UserStateRepository {
    override fun findStatusByUserId(userId: Long): UserState? {
        return jdbcTemplate.query(
            "SELECT user_id, online, last_active FROM user_state WHERE user_id = ?",
            arrayOf(userId),
        ) { rs, _ ->
            UserState(
                userId = rs.getLong("user_id"),
                online = rs.getBoolean("online"),
                lastActive = rs.getObject("last_active", OffsetDateTime::class.java),
            )
        }.firstOrNull()
    }

    override fun updateStatusByUserId(
        userId: Long,
        online: Boolean,
    ) {
        jdbcTemplate.update(
            "UPDATE online SET status = ?, last_active = ? WHERE user_id = ?",
            online,
            OffsetDateTime.now(),
            userId,
        )
    }
}
