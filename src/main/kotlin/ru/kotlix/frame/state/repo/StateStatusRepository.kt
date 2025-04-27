package ru.kotlix.frame.state.repo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.kotlix.frame.state.dto.UserState
import java.time.LocalDateTime


@Repository
class StateStatusRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    fun findStatusByUserId(userId: Long): UserState? {
        return jdbcTemplate.query(
            "SELECT user_id, online, last_active FROM user_state WHERE user_id = ?",
            arrayOf(userId)
        ) { rs, _ ->
            UserState(
                userId = rs.getLong("user_id"),
                online = rs.getBoolean("online"),
                lastActive = rs.getTimestamp("last_active").toLocalDateTime()
            )
        }.firstOrNull()
    }

    fun updateStatusByUserId(userId: Long, online: Boolean) {
        jdbcTemplate.update(
            "UPDATE online SET status = ?, last_active = ? WHERE user_id = ?",
            online,
            LocalDateTime.now(),
            userId
        )
    }
}